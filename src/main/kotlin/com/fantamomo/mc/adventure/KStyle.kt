package com.fantamomo.mc.adventure

import net.kyori.adventure.text.format.Style

@ComponentDsl
class KStyle : Styleable<Style.Builder>{
    override val builder: Style.Builder = Style.style()

    fun build() = builder.build()
}

fun KComponentBuilder<*, *>.style(style: Style) {
    builder.style(style)
}

fun KComponentBuilder<*, *>.style(builder: KStyle.() -> Unit) {
    val style = KStyle()
    style.builder()
    style(style.build())
}