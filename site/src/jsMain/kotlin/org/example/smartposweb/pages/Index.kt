package org.example.smartposweb.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import org.example.smartposweb.InputField
import org.example.smartposweb.Spacer
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorString by remember { mutableStateOf<String?>(null) }
    val ctx = rememberPageContext()

    val rememberCoroutineScope = rememberCoroutineScope()
    Box(
        Modifier.fillMaxSize().minHeight(100.percent)
            // Create a box with two rows: the main content (fills as much space as it can) and the footer (which reserves
            // space at the bottom). "auto" means the use the height of the row. "1fr" means give the rest of the space to
            // that row. Since this box is set to *at least* 100%, the footer will always appear at least on the bottom but
            // can be pushed further down if the first row grows beyond the page.
            .gridTemplateRows("1fr auto")
    ) {
        Column(
            modifier = Modifier.fillMaxSize().textAlign(TextAlign.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image("logo.png")
            Spacer(10.px)
            InputField(icon = "user.png", placeholder = "Login") { username = it }
            Spacer(10.px)
            InputField(icon = "padlock.png", placeholder = "Password") { password = it }
            Spacer(10.px)
            Button(onClick = {
                console.log("CLICKED!!")
                rememberCoroutineScope.launch {
                    val loginResult = httpClient.post("/api/admin/v1/account/login") {
                        header("App-Type", "APAY_ADMIN")
                        setBody("{\"login\": \"$username\", \"password\": \"$password\"}")
                    }
                    if (loginResult.status == HttpStatusCode.OK) {
                        ctx.router.navigateTo("/home")
                    } else {
                        errorString = loginResult.bodyAsText()
                    }
                }
            }) {
                Text("Sign In")
            }
            errorString?.let {
                Text(it)
            }
        }
    }
}