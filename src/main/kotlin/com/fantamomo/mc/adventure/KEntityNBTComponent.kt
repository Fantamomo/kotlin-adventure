@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.EntityNBTComponent

class KEntityNBTComponent : KComponentBuilder<EntityNBTComponent, EntityNBTComponent.Builder> {
    override val builder: EntityNBTComponent.Builder = Component.entityNBT()

    override fun build() = builder.build()
}

fun KComponentBuilder<*, *>.entityNBT(builder: KEntityNBTComponent.() -> Unit) {
    val entityNBT = KEntityNBTComponent()
    entityNBT.builder()
    append(entityNBT.build())
}

inline fun KEntityNBTComponent.selector(selector: String) {
    builder.selector(selector)
}