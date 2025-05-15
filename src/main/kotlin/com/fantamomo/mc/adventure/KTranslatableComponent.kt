@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.translation.Translatable

class KTranslatableComponent : KComponentBuilder<TranslatableComponent, TranslatableComponent.Builder> {
    override val builder: TranslatableComponent.Builder = Component.translatable()

    override fun build() = builder.build()
}

fun KComponentBuilder<*, *>.translatable(builder: KTranslatableComponent.() -> Unit) {
    val translatable = KTranslatableComponent()
    translatable.builder()
    append(translatable.build())
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