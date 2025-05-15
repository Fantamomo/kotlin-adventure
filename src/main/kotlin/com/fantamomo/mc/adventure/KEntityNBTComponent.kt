@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.EntityNBTComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KEntityNBTComponent : KComponentBuilder<EntityNBTComponent, EntityNBTComponent.Builder> {
    override val builder: EntityNBTComponent.Builder = Component.entityNBT()

    override fun build() = builder.build()
}

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.entityNBT(builder: KEntityNBTComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val entityNBT = KEntityNBTComponent()
    entityNBT.builder()
    append(entityNBT.build())
}

inline fun KEntityNBTComponent.selector(selector: String) {
    builder.selector(selector)
}