@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.StorageNBTComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KStorageNBTComponent(override val builder: StorageNBTComponent.Builder = Component.storageNBT()) :
    KComponentBuilder<StorageNBTComponent, StorageNBTComponent.Builder>

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.storageNBT(builder: KStorageNBTComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val storageNBT = KStorageNBTComponent()
    storageNBT.builder()
    this.builder.append(storageNBT.builder.build())
}

inline fun KStorageNBTComponent.storage(key: Key) {
    builder.storage(key)
}