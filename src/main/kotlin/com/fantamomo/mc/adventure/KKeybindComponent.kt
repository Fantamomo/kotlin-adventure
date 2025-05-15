@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.KeybindComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KKeybindComponent : KComponentBuilder<KeybindComponent, KeybindComponent.Builder> {
    override val builder: KeybindComponent.Builder = Component.keybind()

    override fun build() = builder.build()
}

@OptIn(ExperimentalContracts::class)
fun KComponentBuilder<*, *>.keybind(builder: KKeybindComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val keybind = KKeybindComponent()
    keybind.builder()
    append(keybind.build())
}

inline fun KKeybindComponent.keybind(keybind: String) {
    builder.keybind(keybind)
}

inline fun KKeybindComponent.keybind(keybind: KeybindComponent.KeybindLike) {
    builder.keybind(keybind)
}