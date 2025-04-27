package com.fantamomo.mc.adventure

/**
 * Represents a sealed interface for all types of hover events in the Minecraft component system.
 *
 * The `HoverEventType` interface is parameterized to specify the type of hover event and its corresponding builder.
 * It serves as a basis for defining various hover event types, such as item hover events, text hover events,
 * and entity hover events. Each hover event type corresponds to a specific implementation of `HoverEventType`
 * and uses a builder to configure and create the hover event details.
 *
 * @param T The specific type of hover event, such as `Item`, `Text`, or `Entity`.
 * @param H The builder type associated with a given hover event type.
 */
sealed interface HoverEventType<T : HoverEventType<T, H>, H : HoverEventBuilder<T, H>> {
    /**
     * Represents the hover event type for items in a Minecraft component system.
     *
     * This `Item` object is a concrete implementation of the `HoverEventType` interface, specifying the
     * functionality for creating hover events that display details of an item when a player hovers over
     * a particular component in the game interface.
     *
     * The corresponding hover event builder type for `Item` is `HoverEventBuilder.Item`, which is responsible for
     * constructing the details of the item hover event, such as the item identifier, quantity, and metadata.
     *
     * Overrides:
     * - `create`: Returns a new instance of `HoverEventBuilder.Item`, which can be used to configure
     *   and construct an item hover event.
     */
    data object Item : HoverEventType<Item, HoverEventBuilder.Item> {
        override fun create() = HoverEventBuilder.Item()
    }
    /**
     * Represents the hover event type for displaying text in a Minecraft component system.
     *
     * The `Text` object is a concrete implementation of the `HoverEventType` interface,
     * specifying the functionality for creating hover events that display text when a user
     * hovers over a component.
     *
     * This implementation overrides the `create` method to return an instance of
     * `HoverEventBuilder.Text`, allowing for the configuration and construction of text-based
     * hover events.
     */
    data object Text : HoverEventType<Text, HoverEventBuilder.Text> {
        override fun create() = HoverEventBuilder.Text()
    }
    /**
     * Represents the `Entity` hover event type, a data object implementing the `HoverEventType` interface.
     *
     * The `Entity` object is used to define hover events that display information about entities. This is achieved
     * by using the corresponding builder class `HoverEventBuilder.Entity`, which supports customization of entity properties
     * such as type, unique identifier (UUID), and name. The `create` method initializes a builder instance, allowing further
     * configuration of the hover event for an entity.
     *
     * This object provides a concise way of representing and constructing hover events specifically related to entities.
     *
     * Implements:
     * - `HoverEventType<Entity, HoverEventBuilder.Entity>`
     */
    data object Entity : HoverEventType<Entity, HoverEventBuilder.Entity> {
        override fun create() = HoverEventBuilder.Entity()

    }

    fun create(): H
}