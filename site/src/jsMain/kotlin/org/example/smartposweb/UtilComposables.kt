package org.example.smartposweb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.SilkTheme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Span
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSpanElement

@Composable
fun Spacer(size: CSSNumeric) {
    Div(attrs = {
        style {
            padding(size)
        }
    })
}

@Composable
fun InputField(
    icon: String,
    placeholder: String = "",
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    onInputChanged: (String) -> Unit
) {
    val borderColor = remember { mutableStateOf(Color("#b3b3b3")) }
    Span(attrs = {
        style {
            border(width = 0.5.px, LineStyle.Solid, borderColor.value)
            boxSizing("border-box")
            paddingLeft(11.px)
            paddingRight(11.px)
            paddingTop(4.px)
            paddingBottom(4.px)
            borderRadius(2.px)
            display(DisplayStyle.LegacyInlineFlex)
        }
        attrs?.invoke(this)
    }) {
        Image(icon, modifier = Modifier.size(12.px))
        Input(
            InputType.Text,
            attrs = {
                placeholder(placeholder)
                onInput { e -> onInputChanged(e.value) }
                onFocusIn {
                    borderColor.value = Color("#1890ff")
                }
                onFocusOut {
                    borderColor.value = Color("#b3b3b3")
                }
                style {
                    border(width = 0.px)
                    outline("none")
                }
            }
        )
    }
}


// BELOW CODE COPIED FROM KOBWEB EXAMPLES

//val TextInputLabelStyle = ComponentStyle.base("text-input-label") {
//    Modifier
//        .fontSize(G.Ui.Text.Small)
//        .color(Colors.Grey)
//}
//
//val TextInputStyle = ComponentStyle.base("text-input") {
//    Modifier
//        .width(G.Ui.Width.Medium)
//        .margin(bottom = 10.px)
//        .fontSize(G.Ui.Text.Medium)
//        .borderStyle(LineStyle.Solid)
//}

///** An uncontrolled text input box. */
//@Composable
//fun TextInput1(mask: Boolean = false, ref: ((HTMLInputElement) -> Unit)? = null, onCommit: () -> Unit = {}, onValueChanged: (String) -> Unit) {
//    Input(
//        if (mask) InputType.Password else InputType.Text,
//        attrs = TextInputStyle.toModifier().toAttrs {
//            if (ref != null) {
//                this.ref { element ->
//                    ref(element)
//                    onDispose { }
//                }
//            }
//            onInput { onValueChanged(it.value) }
//            onKeyUp { evt ->
//                if (evt.code == "Enter") {
//                    evt.preventDefault()
//                    onCommit()
//                }
//            }
//        }
//    )
//}
//
///** A controlled text input box. */
//@Composable
//fun TextInput1(
//    text: String,
//    modifier: Modifier = Modifier,
//    mask: Boolean = false,
//    ref: ((HTMLInputElement) -> Unit)? = null,
//    onCommit: () -> Unit = {},
//    onValueChanged: (String) -> Unit
//) {
//    Input(
//        if (mask) InputType.Password else InputType.Text,
//        attrs = TextInputStyle.toModifier().then(modifier).toAttrs {
//            if (ref != null) {
//                this.ref { element ->
//                    ref(element)
//                    onDispose { }
//                }
//            }
//            value(text)
//            onInput { onValueChanged(it.value) }
//            onKeyUp { evt ->
//                if (evt.code == "Enter") {
//                    evt.preventDefault()
//                    onCommit()
//                }
//            }
//        }
//    )
//}