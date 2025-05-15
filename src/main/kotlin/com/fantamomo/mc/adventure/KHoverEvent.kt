package com.fantamomo.mc.adventure

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.DataComponentValue
import net.kyori.adventure.text.event.HoverEvent
import java.util.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed interface KHoverEventType<T : KHoverEventType<T, B, V>, B : KHoverEventBuilder<T, B, V>, V> {
    data object ShowText : KHoverEventType<ShowText, KHoverEventBuilder.ShowText, Component> {
        override fun builder() = KHoverEventBuilder.ShowText()
    }

    data object ShowItem : KHoverEventType<ShowItem, KHoverEventBuilder.ShowItem, HoverEvent.ShowItem> {
        override fun builder() = KHoverEventBuilder.ShowItem()
    }

    data object ShowEntity : KHoverEventType<ShowEntity, KHoverEventBuilder.ShowEntity, HoverEvent.ShowEntity> {
        override fun builder() = KHoverEventBuilder.ShowEntity()
    }

    fun builder(): B
}

sealed interface KHoverEventBuilder<T : KHoverEventType<T, B, V>, B : KHoverEventBuilder<T, B, V>, V> {
    class ShowText : KTextComponent(), KHoverEventBuilder<KHoverEventType.ShowText, ShowText, Component> {

        override fun buildHoverEvent() = HoverEvent.showText(build())
    }

    class ShowItem : KHoverEventBuilder<KHoverEventType.ShowItem, ShowItem, HoverEvent.ShowItem> {
        private lateinit var item: Keyed
        private var count: Int = 1
        var nbt: MutableMap<Key, DataComponentValue> = mutableMapOf()
            private set

        fun item(key: Keyed) {
            item = key
        }

        fun count(count: Int) {
            this.count = count
        }

        fun nbt(block: MutableMap<Key, DataComponentValue>.() -> Unit) {
            nbt = buildMap(block).toMutableMap()
        }

        override fun buildHoverEvent() = HoverEvent.showItem(item, count, nbt)
    }

    class ShowEntity : KHoverEventBuilder<KHoverEventType.ShowEntity, ShowEntity, HoverEvent.ShowEntity> {
        private lateinit var entity: Keyed
        private lateinit var uuid: UUID
        private var name: Component? = null

        fun entity(key: Keyed) {
            entity = key
        }

        fun uuid(uuid: UUID) {
            this.uuid = uuid
        }

        fun name(name: Component) {
            this.name = name
        }

        fun name(block: KTextComponent.() -> Unit) {
            name = textComponent(block)
        }

        override fun buildHoverEvent() = HoverEvent.showEntity(entity, uuid, name)
    }


    fun buildHoverEvent(): HoverEvent<V>
}


fun Styleable<*>.hoverEvent(hoverEvent: HoverEvent<*>) {
    builder.hoverEvent(hoverEvent)
}

@OptIn(ExperimentalContracts::class)
fun <T : KHoverEventType<T, B, V>, B : KHoverEventBuilder<T, B, V>, V> Styleable<*>.hoverEvent(
    type: T,
    builder: B.() -> Unit,
) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val clickEvent = type.builder()
    clickEvent.builder()
    this.builder.hoverEvent(clickEvent.buildHoverEvent())
}