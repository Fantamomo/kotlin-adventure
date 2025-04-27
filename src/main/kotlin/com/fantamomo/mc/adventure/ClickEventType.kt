package com.fantamomo.mc.adventure

/**
 * Represents a sealed interface for various types of click events within a component system.
 *
 * This interface defines a hierarchy of specific click event types, each of which implements
 * specific behavior for interacting with components or entities. Each concrete implementation
 * is responsible for defining how the specific event is constructed and utilized.
 *
 * The generic parameters T and H ensure type safety and enforce a relationship between
 * the click event type and its associated builder.
 *
 * @param T The type that extends ClickEventType, defining the specific click event type.
 * @param H The associated ClickEventBuilder type used to construct the click event.
 */
sealed interface ClickEventType<T : ClickEventType<T, H>, H : ClickEventBuilder<T, H>> {
    /**
     * Represents a click event type for opening a URL.
     *
     * This event type is used to define a click action that triggers
     * opening a specified URL when a component is interacted with.
     *
     * The associated builder is `ClickEventBuilder.OpenUrl`, which allows
     * you to configure the URL to be opened. The `create` method initializes
     * an instance of the builder.
     *
     * This type is part of the hierarchy of `ClickEventType` and ensures
     * type safety when constructing click events associated with URLs.
     */
    data object OpenUrl : ClickEventType<OpenUrl, ClickEventBuilder.OpenUrl> {
        override fun create() = ClickEventBuilder.OpenUrl()
    }
    /**
     * Represents a click event type that opens a file on the user's system.
     *
     * This object is used with the `ClickEventBuilder.OpenFile` builder to create a click event
     * that links to a specific file path. The `OpenFile` event type can be used in components
     * to trigger file-opening functionality when clicked by a user.
     *
     * Implementing the `ClickEventType` interface, it provides a mechanism to generate an instance
     * of the associated `ClickEventBuilder.OpenFile` for configuring the click event.
     */
    data object OpenFile : ClickEventType<OpenFile, ClickEventBuilder.OpenFile> {
        override fun create() =  ClickEventBuilder.OpenFile()
    }
    /**
     * Represents a type of click event used for running a command when clicked.
     *
     * This `RunCommand` object implements the `ClickEventType` interface, allowing it to be utilized in scenarios where
     * a command is executed upon a click interaction within the application's context.
     *
     * The `RunCommand` type is linked with the builder `ClickEventBuilder.RunCommand`, which defines its configuration.
     *
     * Key Features:
     * - Defines a click event type for executing a command.
     * - Associated with the builder `ClickEventBuilder.RunCommand`, which allows setting the desired command.
     *
     * Implements:
     * - `ClickEventType` interface for compatibility with `ClickEventBuilder` types.
     *
     * Methods:
     * - `create`: Creates an instance of the builder associated with this click event type.
     */
    data object RunCommand : ClickEventType<RunCommand, ClickEventBuilder.RunCommand> {
        override fun create() = ClickEventBuilder.RunCommand()
    }
    /**
     * Represents the `SuggestCommand` type of click event.
     *
     * This class is used to create a click event where a specified command will be suggested to the user.
     * The user can then edit or execute the suggested command in their chat interface.
     *
     * It implements the `ClickEventType` interface with itself as the type and uses the
     * corresponding `ClickEventBuilder.SuggestCommand` for building the event.
     *
     * Overrides the `create` method from `ClickEventType` to return a new instance of
     * the `ClickEventBuilder.SuggestCommand` class.
     */
    data object SuggestCommand : ClickEventType<SuggestCommand, ClickEventBuilder.SuggestCommand> {
        override fun create() = ClickEventBuilder.SuggestCommand()
    }
    /**
     * Represents a specific type of click event that changes the page in a paginated context.
     * This is a singleton implementation of the `ClickEventType` interface for the `ChangePage` action.
     *
     * The `ChangePage` event is typically used to navigate between pages in user interfaces such as books
     * or paginated menus, by specifying the target page number.
     *
     * This object allows the creation of a `ClickEventBuilder.ChangePage` instance,
     * which contains the data and behavior needed to define the click event.
     *
     * - `create`: Provides a new instance of `ClickEventBuilder.ChangePage` used for building the specific click event.
     */
    data object ChangePage : ClickEventType<ChangePage, ClickEventBuilder.ChangePage> {
        override fun create() = ClickEventBuilder.ChangePage()
    }
    /**
     * Represents a click event type that copies a specified text to the clipboard when triggered.
     *
     * This event type can be used to create clickable components that enable users
     * to copy predefined text to their clipboard with a single interaction.
     *
     * Implements the `ClickEventType` interface specific to this event and provides
     * a method to create the corresponding `ClickEventBuilder.CopyToClipboard`.
     */
    data object CopyToClipboard : ClickEventType<CopyToClipboard, ClickEventBuilder.CopyToClipboard> {
        override fun create() = ClickEventBuilder.CopyToClipboard()
    }
    /**
     * Represents a custom click event type for components using the `ClickEventType` interface.
     * This type allows defining custom behavior for click events by utilizing a corresponding
     * `Custom` builder in the `ClickEventBuilder` hierarchy.
     *
     * This object overrides the `create` method of the `ClickEventType` interface to provide
     * an instance of the `ClickEventBuilder.Custom`, which can be configured with specific
     * actions and options.
     *
     * Use this type when creating click events that require custom-defined behavior beyond
     * the predefined click event types.
     */
    data object Custom : ClickEventType<Custom, ClickEventBuilder.Custom> {
        override fun create() = ClickEventBuilder.Custom()
    }

    /**
     * Creates and returns an instance of type H, which serves as an implementation
     * of the ClickEventBuilder for specific click event types.
     *
     * @return An instance of type H configured for the corresponding click event type.
     */
    fun create(): H
}