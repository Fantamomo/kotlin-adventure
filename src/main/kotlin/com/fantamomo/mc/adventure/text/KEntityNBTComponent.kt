@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.EntityNBTComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KEntityNBTComponent(override val builder: EntityNBTComponent.Builder = Component.entityNBT()) :
    KComponentBuilder<EntityNBTComponent, EntityNBTComponent.Builder>

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.entityNBT(builder: KEntityNBTComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val entityNBT = KEntityNBTComponent()
    entityNBT.builder()
    this.builder.append(entityNBT.builder.build())
}

inline fun KEntityNBTComponent.selector(selector: String) {
    builder.selector(selector)
}