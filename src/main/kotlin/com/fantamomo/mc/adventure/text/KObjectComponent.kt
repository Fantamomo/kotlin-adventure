package com.fantamomo.mc.adventure.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ObjectComponent
import net.kyori.adventure.text.`object`.ObjectContents
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class KObjectComponent(override val builder: ObjectComponent.Builder = Component.`object`()) : KComponentBuilder<ObjectComponent, ObjectComponent.Builder>

@OptIn(ExperimentalContracts::class)
inline fun KComponentBuilder<*, *>.objectComponent(builder: KObjectComponent.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val objectComponent = KObjectComponent()
    objectComponent.builder()
    this.builder.append(objectComponent.builder.build())
}

fun KObjectComponent.contents(contents: ObjectContents) {
    builder.contents(contents)
}

@OptIn(ExperimentalContracts::class)
inline fun KObjectComponent.playerHead(builder: KPlayerHeadObjectContents.() -> Unit) {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    val playerHeadContents = KPlayerHeadObjectContents()
    playerHeadContents.builder()
    contents(playerHeadContents.build())
}

fun KObjectComponent.sprite(atlas: Key, sprite: Key) {
    contents(ObjectContents.sprite(atlas, sprite))
}

fun KObjectComponent.sprite(sprite: Key) {
    contents(ObjectContents.sprite(sprite))
}