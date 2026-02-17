@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.translation.Translatable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KTranslatableComponent(override val builder: TranslatableComponent.Builder = Component.translatable()) :
    KComponentBuilder<TranslatableComponent, TranslatableComponent.Builder>

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.translatable(builder: KTranslatableComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val translatable = KTranslatableComponent()
    translatable.builder()
    this.builder.append(translatable.builder.build())
}
@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.translatable(key: String, builder: KTranslatableComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val adventureBuilder = Component.translatable()
    adventureBuilder.key(key)
    val translatable = KTranslatableComponent(adventureBuilder)
    translatable.builder()
    this.builder.append(translatable.builder.build())
}

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.translatable(key: String) {
    this.builder.append(Component.translatable(key))
}

inline fun KTranslatableComponent.key(translatable: Translatable) {
    builder.key(translatable)
}

inline fun KTranslatableComponent.key(key: String) {
    builder.key(key)
}

inline fun KTranslatableComponent.args(args: List<ComponentLike>) {
    builder.arguments(args)
}

inline fun KTranslatableComponent.args(vararg args: ComponentLike) {
    builder.arguments(*args)
}

inline fun KTranslatableComponent.fallback(fallback: String) {
    builder.fallback(fallback)
}