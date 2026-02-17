@file:OptIn(ExperimentalContracts::class)
@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TranslationArgument
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
    args.add(textComponent(builder))
}

inline fun KTranslatableArgsBuilder.arg(component: ComponentLike) {
    args.add(component)
}

inline fun KTranslatableArgsBuilder.bool(bool: Boolean) {
    args.add(TranslationArgument.bool(bool))
}

inline fun KTranslatableArgsBuilder.numeric(number: Number) {
    args.add(TranslationArgument.numeric(number))
}

inline fun KTranslatableArgsBuilder.component(component: ComponentLike) {
    args.add(TranslationArgument.component(component))
}

inline fun KTranslatableArgsBuilder.component(builder: KComponentBuilder<*, *>.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    component(textComponent(builder))
}