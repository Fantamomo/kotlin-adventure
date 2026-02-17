@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.KeybindComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KKeybindComponent(override val builder: KeybindComponent.Builder = Component.keybind()) :
    KComponentBuilder<KeybindComponent, KeybindComponent.Builder>

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.keybind(builder: KKeybindComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val keybind = KKeybindComponent()
    keybind.builder()
    this.builder.append(keybind.builder.build())
}

inline fun KKeybindComponent.keybind(keybind: String) {
    builder.keybind(keybind)
}

inline fun KKeybindComponent.keybind(keybind: KeybindComponent.KeybindLike) {
    builder.keybind(keybind)
}