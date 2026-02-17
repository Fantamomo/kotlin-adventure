package com.fantamomo.mc.adventure.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.`object`.ObjectContents
import net.kyori.adventure.text.`object`.PlayerHeadObjectContents
import java.util.UUID

@ComponentDsl
interface KObjectContents<C : ObjectContents> {
    fun build(): C
}

class KPlayerHeadObjectContents : KObjectContents<PlayerHeadObjectContents> {
    @PublishedApi
    internal val builder: PlayerHeadObjectContents.Builder = ObjectContents.playerHead()

    override fun build(): PlayerHeadObjectContents = builder.build()
}

fun KPlayerHeadObjectContents.id(uuid: UUID?) {
    builder.id(uuid)
}

fun KPlayerHeadObjectContents.name(name: String?) {
    builder.name(name)
}

fun KPlayerHeadObjectContents.profileProperty(profileProperty: PlayerHeadObjectContents.ProfileProperty) {
    builder.profileProperty(profileProperty)
}

fun KPlayerHeadObjectContents.profileProperties(profileProperties: Collection<PlayerHeadObjectContents.ProfileProperty>) {
    builder.profileProperties(profileProperties)
}

fun KPlayerHeadObjectContents.skin(skinSource: PlayerHeadObjectContents.SkinSource) {
    builder.skin(skinSource)
}

fun KPlayerHeadObjectContents.hat(hat: Boolean) {
    builder.hat(hat)
}

fun KPlayerHeadObjectContents.texture(texture: Key) {
    builder.texture(texture)
}