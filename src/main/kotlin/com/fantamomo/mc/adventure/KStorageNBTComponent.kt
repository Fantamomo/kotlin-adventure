@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.StorageNBTComponent

class KStorageNBTComponent : KComponentBuilder<StorageNBTComponent, StorageNBTComponent.Builder> {
    override val builder: StorageNBTComponent.Builder = Component.storageNBT()

    override fun build() = builder.build()
}

fun KComponentBuilder<*, *>.storageNBT(builder: KStorageNBTComponent.() -> Unit) {
    val storageNBT = KStorageNBTComponent()
    storageNBT.builder()
    append(storageNBT.build())
}

inline fun KStorageNBTComponent.storage(key: Key) {
    builder.storage(key)
}