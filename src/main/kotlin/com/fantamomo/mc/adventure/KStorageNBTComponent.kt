@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.StorageNBTComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KStorageNBTComponent : KComponentBuilder<StorageNBTComponent, StorageNBTComponent.Builder> {
    override val builder: StorageNBTComponent.Builder = Component.storageNBT()

    override fun build() = builder.build()
}

@OptIn(ExperimentalContracts::class)
fun KComponentBuilder<*, *>.storageNBT(builder: KStorageNBTComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val storageNBT = KStorageNBTComponent()
    storageNBT.builder()
    append(storageNBT.build())
}

inline fun KStorageNBTComponent.storage(key: Key) {
    builder.storage(key)
}