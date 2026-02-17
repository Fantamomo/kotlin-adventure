@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.format.StyleSetter
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

@Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA")
interface KStyleable<S : StyleSetter<*>> {
    val builder: S
}

inline fun KStyleable<*>.color(color: TextColor) {
    builder.color(color)
}

inline fun KStyleable<*>.decorate(decoration: TextDecoration) {
    builder.decorate(decoration)
}

inline fun KStyleable<*>.decorate(vararg decorations: TextDecoration) {
    builder.decorate(*decorations)
}

inline fun KStyleable<*>.font(key: Key) {
    builder.font(key)
}

inline fun KStyleable<*>.insertion(insertion: String) {
    builder.insertion(insertion)
}