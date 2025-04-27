package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component

/**
 * Creates a text component using the provided builder block for customization.
 *
 * @param block The builder lambda that configures the `TextComponentBuilder` to create the desired text component.
 * @return The constructed text component as a `Component` object.
 */
fun component(block: TextComponentBuilder.() -> Unit): Component {
    val builder = TextComponentBuilder()
    builder.block()
    return builder.build()
}