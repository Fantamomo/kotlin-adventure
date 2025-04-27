package com.fantamomo.mc.adventure

import net.kyori.adventure.text.BuildableComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.ComponentBuilder as KyoriComponentBuilder

/**
 * A DSL builder for constructing components in a structured and configurable way.
 *
 * This abstract class represents a base builder for constructing components
 * by appending subcomponents and building them into a final structure.
 * It integrates with the Kyori Adventure library to enable seamless creation
 * and manipulation of text components with styling and interaction capabilities.
 *
 * The builder uses a generic type parameter `C` for the target component being built
 * and `B` for the underlying builder implementation. This ensures type safety
 * and flexibility when working with various component types.
 *
 * Functions in this builder allow appending child components in different ways
 * (through other builders, existing components, or raw text content) and
 * creating the final component structure.
 *
 * @param C The type of the component being built.
 * @param B The type of the underlying builder implementation.
 */
@ComponentDsl
abstract class ComponentBuilder<C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> {
    /**
     * The underlying builder instance used for constructing the desired component.
     *
     * This abstract property represents the core builder implementation associated with the `ComponentBuilder`.
     * It serves as the delegate for building the component structure and appending subcomponents or styles.
     *
     * The type parameter `B` corresponds to the specific implementation of `KyoriComponentBuilder`.
     * This ensures that all operations performed using the builder are type-safe and consistent with the
     * intended component structure.
     *
     * Subclasses must provide an implementation of this property to define the specific builder behavior.
     */
    internal abstract val builder: B


    fun append(component: ComponentBuilder<C, B>) {
        builder.append(component.build())
    }

    fun append(component: KyoriComponentBuilder<*, *>) {
        builder.append(component)
    }
    
    fun append(component: Component) {
        builder.append(component)
    }

    open fun build(): C = builder.build()
}

/**
 * Adds a keybinding component to the current component being built.
 *
 * This function allows constructing and appending a keybinding component to the
 * current `ComponentBuilder` using a DSL-style configuration block. The block
 * provides access to the `KeybindingComponentBuilder` for defining the keybinding
 * properties.
 *
 * The keybinding component can be used to represent a keybinding action in textual
 * form, which is commonly used in Minecraft chat or UI components to display
 * actions tied to specific keybinds.
 *
 * @param block A lambda function that receives the `KeybindingComponentBuilder`
 * as its receiver, allowing customization of the keybinding component properties.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> ComponentBuilder<C, B>.key(block: KeybindingComponentBuilder.() -> Unit) {
    val builder = KeybindingComponentBuilder()
    builder.block()
    append(builder.build())
}

/**
 * Adds a translatable component to the `ComponentBuilder` using the specified customization block.
 *
 * This function creates a `TranslatableComponentBuilder` and applies the provided block to it,
 * allowing customization of the translatable component's content. The created component is appended
 * to the current builder's structure.
 *
 * @param block A lambda function used to configure the `TranslatableComponentBuilder`.
 *              This defines the content and behavior of the translatable component being added.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> ComponentBuilder<C, B>.translate(block: TranslatableComponentBuilder.() -> Unit) {
    val builder = TranslatableComponentBuilder()
    builder.block()
    append(builder.build())
}

/**
 * Adds a score component to the current component being built.
 *
 * This function provides a DSL block to customize the score component through the
 * `ScoreComponentBuilder`. The configured score component is appended to the
 * current component builder.
 *
 * @param block The customization block for configuring the `ScoreComponentBuilder`.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> ComponentBuilder<C, B>.score(block: ScoreComponentBuilder.() -> Unit) {
    val builder = ScoreComponentBuilder()
    builder.block()
    append(builder.build())
}

/**
 * Appends a text component to the current component builder, constructed using the provided builder block.
 *
 * This function utilizes the `TextComponentBuilder` to allow for DSL-style creation
 * and customization of a text component. The resulting text component is appended
 * to the current component builder.
 *
 * @param block A lambda with receiver that configures the `TextComponentBuilder` to define the text component properties.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> ComponentBuilder<C, B>.text(/*text: String? = null,*/ block: TextComponentBuilder.() -> Unit) {
    val builder = TextComponentBuilder()
    builder.block()
    append(builder.build())
}

/**
 * Applies a text decoration to the component being built.
 *
 * @param decoration The text decoration to apply, such as bold, italic, or underline.
 * @param flag Determines whether the decoration should be applied (true) or removed (false). Defaults to true.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> ComponentBuilder<C, B>.decorate(decoration: TextDecoration, flag: Boolean = true) {
    builder.decoration(decoration, flag)
}

/**
 * Applies the specified style configuration to the builder.
 *
 * This function allows configuring the style of the component being built
 * by applying the provided lambda to a `StyleBuilder` instance.
 *
 * @param style A lambda that configures the `StyleBuilder` to customize the style of the component.
 *              The lambda allows setting properties such as color and decorations.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> ComponentBuilder<C, B>.style(style: StyleBuilder.() -> Unit) {
    builder.style(StyleBuilder().apply(style).build())
}

/**
 * Sets the color of the component being built.
 *
 * This function applies the specified `TextColor` to the current component
 * being constructed within the DSL context.
 *
 * @param color The `TextColor` to set for the component.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>> ComponentBuilder<C, B>.color(color: TextColor) {
    builder.color(color)
}

/**
 * Adds a hover event to the component being built using the specified type and configuration block.
 *
 * This function creates a hover event of the given type, configures it using the provided block,
 * and applies it to the component being constructed by the builder.
 *
 * @param type The type of hover event to create, defined by the `HoverEventType` interface.
 * @param block A lambda used to configure the hover event through the corresponding builder.
 * @param C The type of the component being built.
 * @param B The type of the builder implementation for the component.
 * @param T The specific type of `HoverEventType`.
 * @param H The specific type of `HoverEventBuilder` corresponding to the hover event type.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>, T : HoverEventType<T, H>, H : HoverEventBuilder<T, H>> ComponentBuilder<C, B>.hoverEvent(type: HoverEventType<T, H>, block: H.() -> Unit) {
    val builder: H = type.create()
    builder.block()

    this.builder.hoverEvent(builder.asHoverEvent())
}

/**
 * Adds a click event to the component builder. The click event is defined by the specified
 * type and configured using the provided builder block.
 *
 * @param type The specific click event type to be created, such as opening a URL, running a command, etc.
 *             Must implement the `ClickEventType` interface that provides a corresponding builder.
 * @param block A lambda function used to configure the click event builder of the specified type.
 *              This block is applied to the builder to define the behavior of the click event.
 * @param C The type of the component being built. This must extend from `BuildableComponent`.
 * @param B The builder type associated with the component. This must extend from `KyoriComponentBuilder`.
 * @param T The specific type of the `ClickEventType`.
 * @param H The associated builder type for the specified `ClickEventType`.
 */
fun <C : BuildableComponent<C, B>, B : KyoriComponentBuilder<C, B>, T : ClickEventType<T, H>, H : ClickEventBuilder<T, H>> ComponentBuilder<C, B>.clickEvent(type: ClickEventType<T, H>, block: H.() -> Unit) {
    val builder: H = type.create()
    builder.block()

    this.builder.clickEvent(builder.asClickEvent())
}


