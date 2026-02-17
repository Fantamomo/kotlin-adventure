package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.BuildableComponent
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.ComponentLike

@ComponentDsl
interface KComponentBuilder<C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> : KStyleable<B> {
    override val builder: B
}

@Suppress("NOTHING_TO_INLINE")
inline fun KComponentBuilder<*, *>.append(component: ComponentLike) {
    builder.append(component)
}