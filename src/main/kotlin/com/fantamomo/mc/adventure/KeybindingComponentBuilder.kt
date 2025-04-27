package com.fantamomo.mc.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.KeybindComponent

/**
 * A DSL builder for constructing keybind components.
 *
 * This builder facilitates the creation of `KeybindComponent` instances,
 * which represent components displaying keybindings in a text format.
 * These keybind components are used to show the localized name of a key
 * defined in a game's controls, making it useful in contexts where key control representation is needed.
 *
 * The builder internally leverages the `KeybindComponent.Builder` from the Kyori Adventure library
 * to provide a flexible and type-safe means of configuring such components.
 *
 * This class extends `ComponentBuilder`, inheriting its functionality for managing
 * underlying builder operations and appending child components or additional styling.
 */
@ComponentDsl
class KeybindingComponentBuilder : ComponentBuilder<KeybindComponent, KeybindComponent.Builder>() {
    override val builder = Component.keybind()
}

/**
 * Sets the keybinding for this `KeybindingComponentBuilder`.
 *
 * This defines the keybind to be displayed in the component, allowing
 * the representation of localized key names associated with controls in a game.
 *
 * @param key The identifier of the keybind to use. This should correspond
 * to a keybind name defined in the game's control settings.
 */
fun KeybindingComponentBuilder.key(key: String) {
    builder.keybind(key)
}