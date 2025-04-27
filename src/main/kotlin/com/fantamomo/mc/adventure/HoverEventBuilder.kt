package com.fantamomo.mc.adventure

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.DataComponentValue
import net.kyori.adventure.text.event.HoverEvent
import java.util.*

/**
 * Represents a customizable builder interface for constructing hover events
 * in the context of Minecraft components using the provided DSL.
 *
 * A `HoverEventBuilder` is a sealed interface where specific implementations pertain
 * to creating hover events for different types, such as items, text, or entities.
 *
 * @param T The type of hover event being built.
 * @param H The specific builder implementation corresponding to the hover event type.
 */
@ComponentDsl
sealed interface HoverEventBuilder<T : HoverEventType<T, H>, H : HoverEventBuilder<T, H>> {
    /**
     * Represents a builder for constructing hover events of the `Item` type in Minecraft components.
     *
     * The `Item` class is used to define the properties of an item being shown in a hover event,
     * such as its identifier (`key`), the quantity (`count`), and additional metadata (`nbt`).
     *
     * This DSL class allows the configuration of these properties to create a hover event
     * that, when displayed in-game, will show details about the specified item.
     *
     * Properties:
     *
     * - `key`: Specifies the unique identifier of the item.
     * - `count`: The quantity of the item, default is 1.
     * - `nbt`: A mutable map that stores additional metadata for the item.
     *
     * The method `asHoverEvent` generates the hover event for the configured item.
     */
    @ComponentDsl
    class Item : HoverEventBuilder<HoverEventType.Item, Item> {
        lateinit var key: Key
        var count: Int = 1
        var nbt: MutableMap<Key, out DataComponentValue> = mutableMapOf()

        override fun asHoverEvent() = HoverEvent.showItem(key, count, nbt)
    }

    /**
     * A builder for constructing text components within the DSL context. This class extends
     * `TextComponentBuilder` and implements `HoverEventBuilder` for `HoverEventType.Text`.
     *
     * The `Text` class is designated as part of the component DSL using the `@ComponentDsl` annotation.
     * It provides functionality to create and configure text components and customize hover events
     * displaying the constructed text component.
     *
     * The primary feature of this class is its ability to produce a `HoverEvent` configured to
     * display the built text component when hovered over.
     *
     * @see TextComponentBuilder
     * @see HoverEventBuilder
     */
    @ComponentDsl
    class Text : TextComponentBuilder(), HoverEventBuilder<HoverEventType.Text, Text> {

        override fun asHoverEvent() = HoverEvent.showText(this.build())
    }

    /**
     * Represents a DSL builder for constructing components related to the `HoverEvent` type `Entity`.
     *
     * This class allows configuration of an entity's hover event data, including its type, unique identifier (UUID),
     * and optionally a custom name. It implements the `HoverEventBuilder` interface for the `Entity` hover event type,
     * enabling the creation of hover events that display entity-related information.
     *
     * The class utilizes `@ComponentDsl` to enforce DSL-specific context during usage and restricts the scope of DSL constructs.
     *
     * @property type The type of the entity as a unique key, required for the hover event.
     * @property uuid The universally unique identifier (UUID) of the entity, required for the hover event.
     * @property name An optional custom name for the entity, defined using a `TextComponentBuilder`.
     */
    @ComponentDsl
    class Entity : HoverEventBuilder<HoverEventType.Entity, Entity> {
        lateinit var type: Key
        lateinit var uuid: UUID
        internal var name: Component? = null

        override fun asHoverEvent() = HoverEvent.showEntity(type, uuid, name)

        /**
         * Sets a custom name for the entity using a `TextComponentBuilder`.
         *
         * @param block The builder lambda that configures the `TextComponentBuilder` to create the desired custom name for the entity.
         */
        fun Entity.name(block: TextComponentBuilder.() -> Unit) {
            val builder = TextComponentBuilder()
            builder.block()
            name = builder.build()
        }

    }

    /**
     * Constructs and returns a `HoverEvent` instance based on the current state of the builder.
     * The returned `HoverEvent` can be used to define hover interactions in components, such as
     * displaying a tooltip or additional information when a user hovers over a specific element.
     *
     * @return A `HoverEvent` object representing the configured hover interaction.
     */
    fun asHoverEvent(): HoverEvent<*>
}