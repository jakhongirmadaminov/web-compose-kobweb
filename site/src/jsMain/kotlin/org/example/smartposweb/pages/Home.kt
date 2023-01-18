package org.example.smartposweb.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import org.example.smartposweb.components.layouts.PageLayout
import org.jetbrains.compose.web.dom.Text


@Page
@Composable
fun Home() {
    PageLayout("Home"){
        Text("SIGNED IN!!!!")
    }
}