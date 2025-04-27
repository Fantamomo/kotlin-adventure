package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent

/**
 * A DSL builder for constructing text components.
 *
 * The `TextComponentBuilder` class provides a simplified and structured way to create
 * text components using the Kyori Adventure library. It extends the `ComponentBuilder`
 * class to inherit the base functionality for assembling components and integrates
 * specific methods and properties tailored for text components.
 *
 * This builder uses an underlying `TextComponent.Builder` instance to facilitate
 * the construction of textual content with optional style and interaction features.
 *
 * The `@ComponentDsl` annotation restricts the usage of this builder within a scoped
 * DSL context for structured component construction.
 *
 * Functions and properties inherited from the parent `ComponentBuilder` class allow
 * appending child components, integrating styles, and finalizing the construction of
 * the text component.
 */
@ComponentDsl
open class TextComponentBuilder() : ComponentBuilder<TextComponent, TextComponent.Builder>() {
    override val builder = Component.text()
}

/**
 * Sets the content of the text component being constructed with the specified text.
 *
 * This function assigns the provided textual content to the underlying text component
 * builder, which will eventually be used to construct the final text component.
 *
 * @param text The textual content to be set in the text component.
 */
fun TextComponentBuilder.content(text: String) {
    builder.content(text)
}