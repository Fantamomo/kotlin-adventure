@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.BlockNBTComponent
import net.kyori.adventure.text.Component
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KBlockNBTComponent : KComponentBuilder<BlockNBTComponent, BlockNBTComponent.Builder> {
    override val builder: BlockNBTComponent.Builder = Component.blockNBT()

    override fun build() = builder.build()
}

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.blockNBT(builder: KBlockNBTComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val blockNBT = KBlockNBTComponent()
    blockNBT.builder()
    append(blockNBT.build())
}

inline fun KBlockNBTComponent.pos(pos: BlockNBTComponent.Pos) {
    builder.pos(pos)
}

inline fun KBlockNBTComponent.nbtPath(nbtPath: String) {
    builder.nbtPath(nbtPath)
}

inline fun KBlockNBTComponent.separator(separator: Component) {
    builder.separator(separator)
}

inline fun KBlockNBTComponent.worldPos(worldPos: BlockNBTComponent.WorldPos) {
    builder.pos(worldPos)
}