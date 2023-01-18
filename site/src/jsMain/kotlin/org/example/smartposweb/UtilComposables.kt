package org.example.smartposweb

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.dom.Div

@Composable
fun Spacer(size: CSSNumeric) {
    Div(attrs = {
        style {
            padding(size)
        }
    })
}