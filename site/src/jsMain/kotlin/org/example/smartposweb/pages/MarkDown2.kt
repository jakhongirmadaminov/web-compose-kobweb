//package org.example.smartposweb.pages
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.rememberCoroutineScope
//import com.varabyte.kobweb.browser.ApiFetcher
//import com.varabyte.kobweb.browser.api
//import com.varabyte.kobweb.core.Page
//import com.varabyte.kobweb.silk.components.navigation.Link
//import kotlinx.browser.window
//import kotlinx.coroutines.launch
//import org.example.smartposweb.components.layouts.PageLayout
//import org.jetbrains.compose.web.dom.P
//import org.jetbrains.compose.web.dom.Text
//import org.w3c.dom.get
//
//@Page
//@Composable
//fun MarkDown2Page() {
//    PageLayout("MARKDOWN2") {
//        LaunchedEffect(Unit) {
//            window.api.post("")
//        }
//        Text("This is a skeleton app used to showcase a basic site made using Kobweb")
//        P()
//        Link("/", "Go Home")
//    }
//}