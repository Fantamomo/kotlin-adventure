@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.format.StyleSetter
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

interface Styleable<S : StyleSetter<S>> {
    val builder: S
}

inline fun Styleable<*>.color(color: TextColor) {
    builder.color(color)
}

inline fun Styleable<*>.decorate(decoration: TextDecoration) {
    builder.decorate(decoration)
}

inline fun Styleable<*>.decorate(vararg decorations: TextDecoration) {
    builder.decorate(*decorations)
}

inline fun Styleable<*>.font(key: Key) {
    builder.font(key)
}

inline fun Styleable<*>.insertion(insertion: String) {
    builder.insertion(insertion)
}