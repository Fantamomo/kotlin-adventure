@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.SelectorComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KSelectorComponent : KComponentBuilder<SelectorComponent, SelectorComponent.Builder> {
    override val builder: SelectorComponent.Builder = Component.selector()

    override fun build() = builder.build()
}

@OptIn(ExperimentalContracts::class)
fun KComponentBuilder<*, *>.selector(builder: KSelectorComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val selector = KSelectorComponent()
    selector.builder()
    append(selector.build())
}

inline fun KSelectorComponent.pattern(pattern: String) {
    builder.pattern(pattern)
}

inline fun KSelectorComponent.separator(separator: ComponentLike) {
    builder.separator(separator)
}