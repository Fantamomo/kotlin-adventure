package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TranslatableComponent

/**
 * A builder for constructing `TranslatableComponent` instances in a structured and configurable way.
 *
 * The `TranslatableComponentBuilder` class extends the `ComponentBuilder` functionality,
 * specifically targeting the creation of `TranslatableComponent` objects which represent
 * translatable text with optional arguments for inserting dynamic content.
 *
 * This class allows defining arguments through a DSL block that utilizes `TextComponentBuilder`
 * for constructing individual argument components. Arguments are stored as a mutable list
 * and are appended to the `TranslatableComponent` during the build process if provided.
 *
 * Properties and Methods:
 * - `builder`: Represents the base `TranslatableComponent.Builder` provided by Kyori Adventure.
 * - `arguments`: A mutable list of DSL blocks to define the arguments for the translatable component.
 * - `build()`: Overrides the base `build` method to integrate arguments into the final `TranslatableComponent`
 *   and returns the constructed component.
 *
 * This class is annotated with `@ComponentDsl` to restrict its usage within a DSL context for modular
 * and scoped component-building processes.
 */
@ComponentDsl
class TranslatableComponentBuilder : ComponentBuilder<TranslatableComponent, TranslatableComponent.Builder>() {
    override val builder = Component.translatable()
    internal val arguments: MutableList<TextComponentBuilder.() -> Unit> = mutableListOf()

    override fun build(): TranslatableComponent {
        if (arguments.isNotEmpty()) builder.arguments(arguments.map { ComponentLike { TextComponentBuilder().apply(it).build() } })
        return builder.build()
    }
}

/**
 * Sets the translation key for the `TranslatableComponent` being built.
 *
 * The translation key is used to reference a localizable text string in a language file.
 *
 * @param key The translation key that identifies the text to be displayed for this component.
 */
fun TranslatableComponentBuilder.key(key: String) {
    builder.key(key)
}

/**
 * Sets the fallback text for the translatable component being built.
 *
 * The fallback text is used as an alternative plain text representation
 * when the translatable key cannot be resolved or displayed in the current context.
 *
 * @param fallback The fallback text to be used for the translatable component.
 */
fun TranslatableComponentBuilder.fallback(fallback: String) {
    builder.fallback(fallback)
}

/**
 * Adds arguments to the `TranslatableComponentBuilder` using a variable number of configuration blocks.
 *
 * @param func A vararg of lambda functions, each configuring a `TextComponentBuilder` to define an
 * individual argument for the translatable component.
 */
fun TranslatableComponentBuilder.arguments(vararg func: TextComponentBuilder.() -> Unit) {
    builder.arguments(func.map { ComponentLike { TextComponentBuilder().apply(it).build() } })
}

/**
 * Adds an argument to the translatable component being built.
 *
 * This function allows appending a new text-based argument to the `TranslatableComponentBuilder`.
 * The argument is defined through a block that configures a `TextComponentBuilder` instance.
 *
 * @param func A lambda block used to configure a `TextComponentBuilder`, which represents the argument to be added.
 */
fun TranslatableComponentBuilder.addArg(func: TextComponentBuilder.() -> Unit) {
    arguments.add(func)
}