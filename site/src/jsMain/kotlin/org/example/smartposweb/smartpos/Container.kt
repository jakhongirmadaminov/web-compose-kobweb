
import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div

@Composable
fun Container(content: @Composable () -> Unit) {
    Div(
        attrs = { classes(AppStylesheet.container) }
    ) {
        content()
    }
}