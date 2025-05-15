package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.Style
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun textComponent(block: KTextComponent.() -> Unit): Component {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    val builder = KTextComponent()
    builder.block()
    return builder.build()
}

inline fun styleBuilder(block: KStyle.() -> Unit): Style {
    val builder = KStyle()
    builder.block()
    return builder.build()
}