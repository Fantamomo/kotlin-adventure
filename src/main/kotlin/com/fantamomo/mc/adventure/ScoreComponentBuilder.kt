package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ScoreComponent

/**
 * A DSL builder for constructing `ScoreComponent` instances.
 *
 * This class is designed to facilitate the creation and configuration of
 * `ScoreComponent` objects within a structured and type-safe context. It extends
 * the base functionality provided by `ComponentBuilder`, leveraging the `ScoreComponent.Builder`
 * from the Kyori Adventure library.
 *
 * This builder integrates with the `@ComponentDsl` context to ensure proper scoping
 * and specialized support for `ScoreComponent` construction.
 *
 * Subcomponents or modifications can be appended using the inherited API from
 * `ComponentBuilder`, which allows for seamless integration and customization.
 *
 * The underlying builder, exposed through the `builder` property, is initialized
 * with an instance acquired via `Component.score()`, representing the entry point for
 * defining and configuring `ScoreComponent` attributes.
 */
@ComponentDsl
class ScoreComponentBuilder : ComponentBuilder<ScoreComponent, ScoreComponent.Builder>() {
    override val builder = Component.score()
}

/**
 * Sets the name for this ScoreComponent using the provided value.
 *
 * @param name The name to be assigned to the ScoreComponent.
 */
fun ScoreComponentBuilder.name(name: String) {
    builder.name(name)
}
/**
 * Sets the objective for the `ScoreComponentBuilder`.
 *
 * This method allows specifying the objective string to associate with the score component
 * being constructed in the `ScoreComponentBuilder` context.
 *
 * @param objective The objective string to set for the score component.
 */
fun ScoreComponentBuilder.objective(objective: String) {
    builder.objective(objective)
}