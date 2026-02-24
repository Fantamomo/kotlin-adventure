@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.JoinConfiguration

inline operator fun Component.plus(other: Component) = this.append(other)

inline fun Array<out ComponentLike>.join(config: JoinConfiguration = JoinConfiguration.noSeparators()): Component =
    this.toList().join(config)

inline fun Iterable<ComponentLike>.join(config: JoinConfiguration = JoinConfiguration.noSeparators()): Component =
    Component.join(config, this)