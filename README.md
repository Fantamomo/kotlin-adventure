# Adventure Kotlin DSL

A lightweight Kotlin DSL wrapper around **[adventure](https://github.com/PaperMC/adventure)**, the powerful text component library from **KyoriPowered and PaperMC**.

This project provides a fully type-safe, idiomatic Kotlin DSL for building Adventure `Component`s with a clean and expressive syntax, while preserving the full flexibility of the original API.

---

## ✨ Why This Library?

While adventure is extremely powerful, its builder-based Java design can feel verbose in Kotlin.

This wrapper:

* ✅ Provides a Kotlin-first DSL
* ✅ Uses Kotlin contracts for safer builders
* ✅ Reduces boilerplate
* ✅ Keeps full compatibility with Adventure components
* ✅ Adds clean, scoped styling and event builders

---

# 🚀 Core Features

## 1️⃣ Kotlin DSL for Text Components

Create complex components using a clean DSL instead of nested builders.

### Example

```kotlin
val component: Component = textComponent {
    text("Hello ")
    text {
        content("World")
        bold()
    }
}
```

This builds a regular Adventure `Component` and is fully compatible with any Adventure platform.

---

## 2️⃣ Full Style Support

Easily apply styles using Kotlin extension functions.

### Supported styling features:

* `color(TextColor)`
* `shadowColor(ARGBLike)`
* `bold()`
* `italic()`
* `underlined()`
* `strikethrough()`
* `obfuscated()`
* `font(Key)`
* `insertion(String)`
* Custom `Style` via DSL

### Example

```kotlin
textComponent {
    text("Styled Text") {
        color(NamedTextColor.RED)
        bold()
        italic()
    }
}
```

You can also build standalone `Style` objects:

```kotlin
val style = styleBuilder {
    bold()
}
```

---

## 3️⃣ Click Events DSL

Full support for all `ClickEvent` types from **adventure**:

* Open URL
* Open file
* Run command
* Suggest command
* Change page
* Copy to clipboard
* Callback
* Show dialog
* Custom events

### Example

```kotlin
textComponent {
    text("Click me!") {
        clickEvent(KClickEventType.OpenUrl) {
            url("https://example.com")
        }
    }
}
```

Callback events with options:

```kotlin
clickEvent(KClickEventType.Callback) {
    callback { audience ->
        audience.sendMessage(Component.text("Clicked!"))
    }
    uses(1)
}
```

---

## 4️⃣ Hover Events DSL

Supports all Adventure hover types:

* Show text
* Show item
* Show entity

### Show Text

```kotlin
hoverEvent(KHoverEventType.ShowText) {
    text("Hover text")
}
```

### Show Item

```kotlin
hoverEvent(KHoverEventType.ShowItem) {
    item(myItemKey)
    count(1)
}
```

### Show Entity

```kotlin
hoverEvent(KHoverEventType.ShowEntity) {
    entity(entityType)
    uuid(uuid)
    name {
        text("Zombie")
    }
}
```

---

## 5️⃣ Translatable Components

Full support for translatable keys and arguments.

### Example

```kotlin
textComponent {
    translatable("chat.type.text") {
        args {
            arg { // this: KTextComponent
                content("Player")
            }
            component { // this: KTextComponent
                content("Hello!")
            }
        }
    }
}
```

The difference between `component` and `arg` is that `component` goes through `TranslationArgument`.
That does nothing on its own, but some platforms may use it to resolve placeholders.

---

## 6️⃣ Specialized Component Types

This DSL supports nearly all advanced component types from Adventure:

### 📌 Text Component

* `text("content")`
* Nested text blocks
* Color overloads

### 📌 Translatable Component

* With arguments builder

### 📌 Keybind Component

```kotlin
keybind {
    keybind("key.jump")
}
```

### 📌 Score Component

```kotlin
score {
    name("Player")
    objective("kills")
}
```

### 📌 Selector Component

```kotlin
selector {
    pattern("@a")
}
```

### 📌 NBT Components

* `blockNBT`
* `entityNBT`
* `storageNBT`

Example:

```kotlin
blockNBT {
    nbtPath("Items[0].tag.display.Name")
}
```

---

## 7️⃣ Object Components (1.20+)

Supports Adventure's object components:

* Sprites
* Player heads

### Player Head Example

```kotlin
objectComponent {
    playerHead {
        name("Notch")
    }
}
```

---

## 8️⃣ Utility Functions

Convenience helpers for layout:

* `newLine()`
* `space()`
* `append(ComponentLike)`

---

## 🧠 Kotlin Contracts

All DSL entry points use Kotlin's `contracts` API:

```kotlin
contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
```

This ensures:

* Safe builder usage
* Improved smart-casting
* Better IDE assistance

---

# 🔒 DSL Safety

The custom `@ComponentDsl` marker prevents accidental scope leakage between nested builders, ensuring clean and predictable DSL usage.

---

# 🧩 Compatibility

* Fully compatible with all platforms using **adventure**
* Produces standard Adventure `Component` objects
* Zero reflection
* Zero overhead beyond builder usage

---
---

# 📖 Philosophy

This project does not replace Adventure.

It enhances it.

You still use:

* `Component`
* `Style`
* `ClickEvent`
* `HoverEvent`

But now in an idiomatic Kotlin way.

---

# ❤️ Example: Full Showcase

```kotlin
val message = textComponent {
    text("Welcome ") {
        color(NamedTextColor.GREEN)
    }

    text("Player") {
        bold()
        clickEvent(KClickEventType.RunCommand) {
            command("/profile")
        }
        hoverEvent(KHoverEventType.ShowText) {
            text("View profile")
        }
    }

    newLine()

    translatable("chat.type.announcement") {
        args {
            arg { content("Server") }
            arg { content("Maintenance starts soon.") }
        }
    }
}
```

---

# 🎯 Summary

This Kotlin DSL wrapper for **adventure** provides:

* Clean Kotlin syntax
* Full feature coverage
* Advanced event support
* Object components support
* NBT component support
* Type-safe builders
* Zero abstraction overhead

If you love Adventure but want a more expressive Kotlin experience — this library is for you.

---

## 🔗 Credits

* **[adventure](https://github.com/PaperMC/adventure)** by **KyoriPowered and PaperMC**

---

## Licence

**[MIT-Licence](LICENCE)**

---

## Author

**Fantamomo**