package com.fantamomo.mc.adventure

import net.kyori.adventure.text.BuildableComponent
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.ComponentLike

@ComponentDsl
interface KComponentBuilder<C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> : KStyleable<B> {
    override val builder: B

    fun build(): C

    fun append(component: ComponentLike) {
        builder.append(component)
    }
}