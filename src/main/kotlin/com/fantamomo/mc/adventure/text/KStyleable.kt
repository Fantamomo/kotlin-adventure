@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.format.StyleSetter
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.util.ARGBLike

@Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA")
interface KStyleable<S : StyleSetter<*>> {
    val builder: S
}

inline fun KStyleable<*>.color(color: TextColor) {
    builder.color(color)
}

inline fun KStyleable<*>.shadowColor(argbLike: ARGBLike) {
    builder.shadowColor(argbLike)
}

inline fun KStyleable<*>.decorate(decoration: TextDecoration) {
    builder.decorate(decoration)
}

inline fun KStyleable<*>.decorate(vararg decorations: TextDecoration) {
    builder.decorate(*decorations)
}

inline fun KStyleable<*>.decoration(decoration: TextDecoration, flag: Boolean) {
    builder.decoration(decoration, flag)
}

inline fun KStyleable<*>.decoration(decoration: TextDecoration, state: TextDecoration.State) {
    builder.decoration(decoration, state)
}

inline fun KStyleable<*>.obfuscated(state: TextDecoration.State = TextDecoration.State.TRUE) =
    decoration(TextDecoration.OBFUSCATED, state)

inline fun KStyleable<*>.bold(state: TextDecoration.State = TextDecoration.State.TRUE) =
    decoration(TextDecoration.BOLD, state)

inline fun KStyleable<*>.strikethrough(state: TextDecoration.State = TextDecoration.State.TRUE) =
    decoration(TextDecoration.STRIKETHROUGH, state)

inline fun KStyleable<*>.italic(state: TextDecoration.State = TextDecoration.State.TRUE) =
    decoration(TextDecoration.ITALIC, state)

inline fun KStyleable<*>.underlined(state: TextDecoration.State = TextDecoration.State.TRUE) =
    decoration(TextDecoration.UNDERLINED, state)

inline fun KStyleable<*>.font(key: Key) {
    builder.font(key)
}

inline fun KStyleable<*>.insertion(insertion: String) {
    builder.insertion(insertion)
}