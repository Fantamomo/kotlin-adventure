@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.format.Style
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ComponentDsl
class KStyle : KStyleable<Style.Builder>{
    override val builder: Style.Builder = Style.style()

    fun build() = builder.build()
}

inline fun KComponentBuilder<*, *>.style(style: Style) {
    builder.style(style)
}

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.style(builder: KStyle.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val style = KStyle()
    style.builder()
    style(style.build())
}