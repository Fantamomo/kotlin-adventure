@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ScoreComponent

class KScoreComponent : KComponentBuilder<ScoreComponent, ScoreComponent.Builder> {
    override val builder: ScoreComponent.Builder = Component.score()

    override fun build() = builder.build()
}

fun KComponentBuilder<*, *>.score(builder: KScoreComponent.() -> Unit) {
    val score = KScoreComponent()
    score.builder()
    append(score.build())
}

inline fun KScoreComponent.name(name: String) {
    builder.name(name)
}

inline fun KScoreComponent.objective(objective: String) {
    builder.objective(objective)
}

inline fun KScoreComponent.value(value: String) {
    builder.value(value)
}