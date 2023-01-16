import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.display

object AppStylesheet : StyleSheet() {
    val container by style { // container is a class
        display(DisplayStyle.Flex)
        // custom property (or not supported out of a box)
        property("font-family", "Arial, Helvetica, sans-serif")
    }
}