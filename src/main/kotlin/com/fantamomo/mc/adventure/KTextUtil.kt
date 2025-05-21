@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component

inline fun KComponentBuilder<*, *>.newLine() {
    builder.append(Component.newline())
}

inline fun KComponentBuilder<*, *>.space() {
    builder.append(Component.space())
}