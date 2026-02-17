@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ScoreComponent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KScoreComponent(override val builder: ScoreComponent.Builder = Component.score()) :
    KComponentBuilder<ScoreComponent, ScoreComponent.Builder>

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.score(builder: KScoreComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val score = KScoreComponent()
    score.builder()
    this.builder.append(score.builder.build())
}

inline fun KScoreComponent.name(name: String) {
    builder.name(name)
}

inline fun KScoreComponent.objective(objective: String) {
    builder.objective(objective)
}