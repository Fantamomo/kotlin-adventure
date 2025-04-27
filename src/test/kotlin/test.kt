import com.fantamomo.mc.adventure.color
import com.fantamomo.mc.adventure.component
import com.fantamomo.mc.adventure.decorate
import com.fantamomo.mc.adventure.style
import com.fantamomo.mc.adventure.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

fun main() {
    component {
        text {
            color(NamedTextColor.RED)
            decorate()
            style {
                color(NamedTextColor.GREEN)
                decorate(TextDecoration.BOLD)
            }
        }
    }
}