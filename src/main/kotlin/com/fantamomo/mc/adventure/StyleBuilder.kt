package com.fantamomo.mc.adventure

import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

/**
 * A builder for creating and configuring styles for text components.
 *
 * The `StyleBuilder` class provides a DSL for setting text color and decorations
 * to define the appearance of text components. It is typically used in conjunction
 * with other component-building DSLs to ensure proper scoping and structure.
 *
 * This class is annotated with `@ComponentDsl` to restrict its usage within the
 * specific DSL context for component building.
 *
 * Properties:
 * - `color`: Defines the text color to be applied. The default value is `null`.
 * - `decorations`: A set of text decorations (e.g., bold, italic, underlined) to
 *   modify the appearance of the text. Managed internally.
 *
 * Functions:
 * - `build()`: Constructs and returns a `Style` object with the configured color
 *   and decorations.
 */
@ComponentDsl
class StyleBuilder {
    var color: TextColor? = null
    internal val decorations = mutableSetOf<TextDecoration>()
    fun build() = Style.style(color, decorations)
}

/**
 * Adds a text decoration to this StyleBuilder.
 *
 * @param decoration The text decoration to be added, such as bold, italic, underline, etc.
 */
fun StyleBuilder.decorate(decoration: TextDecoration) {
    decorations.add(decoration)
}

/**
 * Sets the text color for the style being built by this `StyleBuilder`.
 *
 * @param color The `TextColor` to be applied to the text. This defines the appearance of the text color in the component.
 */
fun StyleBuilder.color(color: TextColor) {
    this.color = color
}