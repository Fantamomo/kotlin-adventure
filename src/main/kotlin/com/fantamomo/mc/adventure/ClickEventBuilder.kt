package com.fantamomo.mc.adventure

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.event.ClickCallback.Options
import net.kyori.adventure.text.event.ClickEvent
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.toJavaDuration

/**
 * Represents a builder for constructing various types of click events.
 *
 * The `ClickEventBuilder` is a sealed interface that defines a hierarchy of specific builders for
 * configuring click events. Each variant corresponds to a distinct click event behavior, enabling
 * flexibility and customization of click actions within components.
 *
 * @param T The specific click event type that extends `ClickEventType`.
 * @param H The associated builder type for the click event.
 */
@ComponentDsl
sealed interface ClickEventBuilder<T : ClickEventType<T, H>, H : ClickEventBuilder<T, H>> {
    /**
     * Represents a builder for creating a click event of type `OpenUrl`.
     *
     * The `OpenUrl` class allows for defining a click event that will open
     * a specified URL when triggered. This builder is part of a DSL for
     * constructing components and their associated interactions.
     *
     * @property url The URL that will be opened when the click event is triggered.
     */
    @ComponentDsl
    class OpenUrl : ClickEventBuilder<ClickEventType.OpenUrl, OpenUrl> {
        lateinit var url: String
        override fun asClickEvent() = ClickEvent.openUrl(url)
    }
    /**
     * A builder class representing a click event type for opening a file.
     *
     * This class is part of the DSL for creating click events in components. It allows
     * specifying a file path that will be opened when the event is triggered. The `file`
     * property is used to define the target file path.
     *
     * The `asClickEvent` method constructs a `ClickEvent` object of type `OpenFile`,
     * which can be used within a component system to interact with the specified file.
     *
     * This class is annotated with `@ComponentDsl`, making it compatible with the
     * component-building DSL.
     */
    @ComponentDsl
    class OpenFile : ClickEventBuilder<ClickEventType.OpenFile, OpenFile> {
        lateinit var file: String
        override fun asClickEvent() = ClickEvent.openFile(file)
    }
    /**
     * A builder class for creating a `RunCommand` click event.
     *
     * This class allows defining a click event that executes a specified command
     * when triggered. The command can be configured using the `command` property.
     *
     * This class extends `ClickEventBuilder` and is associated with the `RunCommand`
     * click event type, ensuring type safety and streamlined integration with
     * other components.
     *
     * Properties:
     * - `command`: The command string to be executed when the click event is triggered.
     *
     * Methods:
     * - `asClickEvent`: Converts this builder into a `ClickEvent`, representing a
     *   `RunCommand` event with the specified command.
     *
     * Usage of this class is optimized for DSL-based component construction.
     */
    @ComponentDsl
    class RunCommand : ClickEventBuilder<ClickEventType.RunCommand, RunCommand> {
        lateinit var command: String
        override fun asClickEvent() = ClickEvent.runCommand(command)
    }
    /**
     * Represents a builder for creating a `SuggestCommand` click event.
     *
     * This class is part of the `ClickEventBuilder` hierarchy, specifically
     * designed to create a click event that suggests a command to the user
     * when the associated component is interacted with. The user can then
     * modify or execute the suggested command as desired.
     *
     * ## Usage
     * - Configure the `command` property with the desired command string.
     * - Use `asClickEvent()` to convert this builder into a `ClickEvent` instance.
     *
     * Features:
     * - Easy to define the suggested command using the `command` property.
     * - Generates a `ClickEvent` of `SuggestCommand` type to be used in components.
     */
    @ComponentDsl
    class SuggestCommand : ClickEventBuilder<ClickEventType.SuggestCommand, SuggestCommand> {
        lateinit var command: String
        override fun asClickEvent() = ClickEvent.suggestCommand(command)
    }
    /**
     * A builder class used to construct a `ChangePage` click event for components.
     *
     * This class is part of a DSL system for defining interaction behaviors in components.
     * It enables the creation of a click event that navigates to a specific page, typically within
     * paginated interfaces like books or menus.
     *
     * The `page` property allows specifying the target page number for the navigation action.
     * The constructed event can be retrieved using the `asClickEvent` method.
     *
     * Implements:
     * - `ClickEventBuilder` for the `ChangePage` click event type.
     *
     * Properties:
     * - `page`: The target page number to navigate to.
     *
     * Methods:
     * - `asClickEvent`: Builds and returns the configured click event.
     */
    @ComponentDsl
    class ChangePage : ClickEventBuilder<ClickEventType.ChangePage, ChangePage> {
        var page: Int = 0
        override fun asClickEvent() = ClickEvent.changePage(page)
    }
    /**
     * A component DSL builder for creating a click event that copies a specified text to the clipboard.
     *
     * This class is used to define the `CopyToClipboard` click event type, which allows for text to be
     * copied to the user's clipboard with a single click interaction. It provides a configurable property,
     * `text`, that specifies the content to be copied when the event is triggered.
     *
     * This builder is part of the component DSL and implements the `ClickEventBuilder` interface for
     * the `CopyToClipboard` click event type.
     *
     * Key Features:
     * - Configurable text to be copied to the clipboard.
     * - Implements the `asClickEvent` method to create and return the corresponding `ClickEvent`.
     */
    @ComponentDsl
    class CopyToClipboard : ClickEventBuilder<ClickEventType.CopyToClipboard, CopyToClipboard> {
        lateinit var text: String
        override fun asClickEvent() = ClickEvent.copyToClipboard(text)
    }
    /**
     * Represents a specialized builder for creating a custom click event.
     *
     * This class extends the `ClickEventBuilder` for `ClickEventType.Custom` and provides a structure to define
     * a custom click event with specific behavior and configuration options. It allows configuring event options
     * and an action to be executed when the event is triggered.
     *
     * This is a primary component in constructing custom click events, which can be utilized where such flexibility
     * is required in interaction handling.
     *
     * Key Features:
     * - Define a custom action to handle the click event using the `onClick` method.
     * - Configure event-specific parameters like maximum uses and lifetime using the `options` method.
     */
    @ComponentDsl
    class Custom : ClickEventBuilder<ClickEventType.Custom, Custom> {
        private var onClick: Audience.() -> Unit = {}
        private val options = OptionsBuilder()
        /**
         * Defines a custom action to be executed when the click event is triggered.
         *
         * @param block A lambda with receiver of type `Audience` that specifies the action to be executed
         * when the click event occurs.
         */
        fun onClick(block: Audience.() -> Unit) {
            onClick = block
        }
        /**
         * A DSL builder class for configuring options related to a component.
         *
         * This class allows customization of several properties such as usage limits
         * and lifetime for a component. By using this builder, you can control the
         * behavior of components with specified constraints.
         *
         * Key Features:
         * - `maxUses`: Defines the maximum number of uses allowed. Defaults to `1`.
         * - `lifetime`: Specifies the duration after which the options expire. Defaults to `12 hours`.
         *
         * A companion object provides default values for these options through constants:
         * - `DEFAULT_USES`: Default maximum uses (value: `1`).
         * - `DEFAULT_LIFETIME`: Default lifetime duration (value: `12 hours`).
         *
         * Once all desired properties are set, the `build` method can be invoked to
         * construct an `Options` instance based on the configured settings.
         * This method is intended for internal use and is not publicly accessible.
         */
        @ComponentDsl
        class OptionsBuilder {
            /**
             * Defines the maximum number of times a component or feature can be used.
             *
             * This variable is designed to restrict the usage of a particular component
             * and defaults to a value of `1`. It can be customized during the configuration
             * process to set a specific usage limit for the component being built.
             *
             * Usage of this value may affect how components behave in contexts where
             * there are constraints or limits to interactions.
             *
             * Defaults:
             * - By default, `maxUses` will be set to `1`.
             */
            var maxUses: Int = 1
            /**
             * Specifies the duration for which the configured options will remain active or valid.
             *
             * This variable determines the lifetime of the options built by the `OptionsBuilder` class.
             * It defines the time span after which the options will expire or become ineffective.
             * The default value is set to 12 hours, but it can be adjusted as needed.
             */
            var lifetime: Duration = 12.hours

            val DEFAULT_USES get() = OptionsBuilder.DEFAULT_USES
            val DEFAULT_LIFETIME get() = OptionsBuilder.DEFAULT_LIFETIME

            companion object {
                const val DEFAULT_USES = 1
                val DEFAULT_LIFETIME = 12.hours
            }
            internal fun build(): Options = Options.builder().uses(maxUses).lifetime(lifetime.toJavaDuration()).build()
        }

        /**
         * Configures options for a component using the provided builder block.
         *
         * This method allows customization of options such as usage limits or lifetime
         * by using an `OptionsBuilder` DSL. The configured options will control the behavior
         * of the component with specified constraints.
         *
         * @param block A lambda with receiver of type `OptionsBuilder` to define and configure the desired options.
         */
        fun options(block: OptionsBuilder.() -> Unit) {
            options.block()
        }

        override fun asClickEvent() = ClickEvent.callback(onClick, options.build())

    }

    /**
     * Converts the current ClickEventBuilder configuration into a ClickEvent instance.
     *
     * @return The constructed ClickEvent based on the current builder state.
     */
    fun asClickEvent(): ClickEvent
}
