import com.fantamomo.mc.adventure.text.KHoverEventType
import com.fantamomo.mc.adventure.text.color
import com.fantamomo.mc.adventure.text.content
import com.fantamomo.mc.adventure.text.decorate
import com.fantamomo.mc.adventure.text.fallback
import com.fantamomo.mc.adventure.text.hoverEvent
import com.fantamomo.mc.adventure.text.key
import com.fantamomo.mc.adventure.text.newLine
import com.fantamomo.mc.adventure.text.style
import com.fantamomo.mc.adventure.text.text
import com.fantamomo.mc.adventure.text.textComponent
import com.fantamomo.mc.adventure.text.translatable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

fun main() {
    val component: Component = textComponent {
        text {
            color(NamedTextColor.RED)
            decorate(TextDecoration.STRIKETHROUGH)
            content("Hello World!")
            style {
                color(NamedTextColor.GREEN)
                decorate(TextDecoration.BOLD)
            }
        }
        text("For normal text")
        text("Or with color", NamedTextColor.BLUE)

        newLine()
        text {
            content("Second Line")
            hoverEvent(KHoverEventType.ShowText) {
                content("Hover ")
                text {
                    content("me!")
                    color(NamedTextColor.YELLOW)
                }
            }
        }
    }
}