@file:OptIn(ExperimentalContracts::class)
@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.ComponentLike
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ComponentDsl
class KTranslatableArgsBuilder {
    @PublishedApi
    internal val args = mutableListOf<ComponentLike>()

    fun toList(): List<ComponentLike> = args
}

inline fun KTranslatableComponent.args(block: KTranslatableArgsBuilder.() -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    val args = KTranslatableArgsBuilder()
    args.block()
    builder.arguments(args.toList())
}

inline fun KTranslatableArgsBuilder.arg(builder: KTextComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val text = KTextComponent()
    text.builder()
    args.add(text.builder.build())
}

inline fun KTranslatableArgsBuilder.arg(component: ComponentLike) {
    args.add(component)
}