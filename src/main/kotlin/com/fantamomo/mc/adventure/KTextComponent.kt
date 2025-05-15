@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent

open class KTextComponent : KComponentBuilder<TextComponent, TextComponent.Builder> {
    override val builder: TextComponent.Builder = Component.text()

    override fun build() = builder.build()
}

fun KComponentBuilder<*, *>.text(builder: KTextComponent.() -> Unit) {
    val text = KTextComponent()
    text.builder()
    append(text.build())
}

inline fun KTextComponent.content(text: String) {
    builder.content(text)
}