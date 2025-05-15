@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.SelectorComponent

class KSelectorComponent : KComponentBuilder<SelectorComponent, SelectorComponent.Builder> {
    override val builder: SelectorComponent.Builder = Component.selector()

    override fun build() = builder.build()
}

fun KComponentBuilder<*, *>.selector(builder: KSelectorComponent.() -> Unit) {
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