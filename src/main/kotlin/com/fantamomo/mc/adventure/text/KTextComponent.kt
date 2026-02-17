@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextColor
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

open class KTextComponent : KComponentBuilder<TextComponent, TextComponent.Builder> {
    override val builder: TextComponent.Builder = Component.text()
}

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.text(builder: KTextComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val text = KTextComponent()
    text.builder()
    this.builder.append(text.builder.build())
}

inline fun KComponentBuilder<*, *>.text(content: String) {
    this.builder.append(Component.text(content))
}

inline fun KComponentBuilder<*, *>.text(content: String, color: TextColor) {
    this.builder.append(Component.text(content, color))
}

inline fun KTextComponent.content(text: String) {
    builder.content(text)
}