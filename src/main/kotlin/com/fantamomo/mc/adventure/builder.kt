package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component

fun textComponent(block: KTextComponent.() -> Unit): Component {
    val builder = KTextComponent()
    builder.block()
    return builder.build()
}