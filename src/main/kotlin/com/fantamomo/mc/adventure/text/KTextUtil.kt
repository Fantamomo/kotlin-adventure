@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.Component

inline fun KComponentBuilder<*, *>.newLine() {
    append(Component.newline())
}

inline fun KComponentBuilder<*, *>.space() {
    append(Component.space())
}