import com.fantamomo.mc.adventure.color
import com.fantamomo.mc.adventure.component
import com.fantamomo.mc.adventure.decorate
import com.fantamomo.mc.adventure.fallback
import com.fantamomo.mc.adventure.key
import com.fantamomo.mc.adventure.style
import com.fantamomo.mc.adventure.text
import com.fantamomo.mc.adventure.translate
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

fun main() {
    component {
        text {
            color(NamedTextColor.RED)
            decorate(TextDecoration.STRIKETHROUGH)
            style {
                color(NamedTextColor.GREEN)
                decorate(TextDecoration.BOLD)
            }
        }
        translate {
            key("test")
            fallback("test")
            color(NamedTextColor.BLUE)
        }
    }
}