package org.example.smartposweb.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
//import io.ktor.client.*
//import io.ktor.client.call.*
//import io.ktor.client.engine.js.*
//import io.ktor.client.plugins.*
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.http.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.DisplayStyle.Companion.Flex
import org.jetbrains.compose.web.css.DisplayStyle.Companion.InlineBlock
import org.jetbrains.compose.web.dom.*

val httpClient by lazy {
    HttpClient(Js) {
        defaultRequest {
            url("https://api-dev.a-pay.uz")
            contentType(ContentType.Application.Json)
        }
    }
}

@Page
@Composable
fun SmartSosAdminLogin() {
    Div(attrs = {
        style {
            margin(-8.px)
            property("font-family", "Arial, Helvetica, sans-serif")
            fontFamily("Gilroy")
            display(Flex)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.SpaceBetween)
            flexDirection(FlexDirection.Column)
            overflowY("scroll")
            height(100.vh)
            width(100.percent)
            backgroundSize("cover")
            backgroundImage("url(https://cabinet-dev.smartpos.uz/c76b6f54bb3a51c6e6bf74bb123b6fb6.png)")
            backgroundColor(Color.white)
        }
    }) {

        Img("logo.png", attrs = {
            style {
                alignContent(AlignContent.Start)
                paddingTop(50.px)
                paddingLeft(150.px)
                alignSelf("start")
            }
        })
        Div(attrs = {
            style {
                display(Flex)
                background("#h4h4h4")
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
                flexDirection(FlexDirection.Column)
            }
        }) {
            Div(attrs = {
                style {
                    width(100.percent)
                    display(Flex)
                    alignItems(AlignItems.Center)
                    justifyContent(JustifyContent.Center)
                    flexDirection(FlexDirection.Column)
                    position(Position.Relative)
                }
            }) {
                Div {
                    LoginTitle()
                    LoginSubtitle()

                    val selectedOne = remember { mutableStateOf(true) }
                    TabSwitch(selectedOne)
                    Form(attrs = {
                        style {
                            display(Flex)
                            marginTop(32.px)
                            flexDirection(FlexDirection.Column)
                            if (selectedOne.value) {
                                minHeight(350.px)
                            }
                        }
                    }) {
                        if (selectedOne.value) {
                            WithLoginPass()
                            InputPassword()
                            rememberMe()
                            loginButton()
                            informText()
                        }
                    }
                }
            }
        }
        Span(attrs = {
            style {
                marginBottom(24.px)
                fontWeight(300)
                marginTop(24.px)
                fontSize(14.px)
            }
        }) {
            Text("© 2019-2022 OOO \"Center for Digital Technology and Innovation\"")
        }
    }

}

@Composable
fun informText() {
    Div(attrs = {
        style {
            width(100.percent)
            display(Flex)
            alignSelf(AlignSelf.Center)
            marginTop(20.px)
            justifyContent(JustifyContent.Center)
        }
    }) {
        Span {
            Text("Вы впервые заходите в личный кабинет?")
        }
    }

    Div(attrs = {
        style {
            width(100.percent)
            display(Flex)
            alignSelf(AlignSelf.Center)
            marginTop(4.px)
            justifyContent(JustifyContent.Center)
        }
    }) {
        A(href = "https://cabinet-dev.smartpos.uz/user/sign-up/init", attrs = {
            style {
                cursor("pointer")
            }
        }) {
            Span(attrs = {
                style {
                    fontSize(16.px)
                    textDecorationLine("underline")
                }
            }) {
                Text("Регистрация")
            }
        }
    }


}

@Composable
fun loginButton() {
    Div(attrs = {
        style {
            display(Flex)
            width(100.percent)
            marginTop(56.px)
            justifyContent(JustifyContent.Center)
        }
    }) {
        val rememberCoroutineScope = rememberCoroutineScope()
        Div(attrs = {
            onClick {
                console.log("CLICKED!!")
                rememberCoroutineScope.launch {
                    httpClient.post("/api/admin/v1/account/login") {
                        header("App-Type", "APAY_ADMIN")
                        setBody("{\"login\": \"apayadmin\", \"password\": \"@dmin4@pay\"}")
                    }
                }
            }
            style {
                display(Flex)
                justifyContent(JustifyContent.Center)
                color(rgb(255, 255, 255))
                minWidth(200.px)
                fontSize(16.px)
                borderRadius(8.px)
                height(30.px)
                paddingTop(12.px)
                paddingBottom(12.px)
                paddingRight(15.px)
                paddingLeft(15.px)
                cursor("pointer")
                backgroundColor(rgb(21, 94, 239))
                borderWidth(0.px)
            }
        }) {
            Span(attrs = {
                style {
                    alignSelf(AlignSelf.Center)
                }
            }) {
                Text("Войти")
            }
        }
    }
}

suspend inline fun <reified T> HttpClient.postJson(
    urlAddress: String,
    block: HttpRequestBuilder.() -> Unit = {}
): T {
    val response = post {
        contentType(ContentType.Application.Json)
        url.takeFrom(urlAddress)
        block()
    }
    if (response.status == HttpStatusCode.OK) {
        return response.body()
    } else {
        throw ServerResponseException(response, response.bodyAsText())
    }
}

@Composable
fun rememberMe() {
    Div(attrs = {
        style {
            width(100.percent)
            display(Flex)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.SpaceBetween)
        }
    }) {
        Div {
            Label {
                Span {
                    Input(InputType.Checkbox) {
                    }
                }
                Span {
                    Text("Запомнить меня")
                }
            }
        }
        Span {
            Text("Забыли пароль?")
        }
    }
}

@Composable
fun WithLoginPass() {
    Div(attrs = {
        style {
            boxSizing("border-box")
            margin(0.px)
            padding(0.px)
            property("vertical-align", "top")
            fontSize(14.px)
            alignContent(AlignContent.Start)
            paddingBottom(16.px)

        }
    }) {
        Div(attrs = {
            style {
                display(InlineBlock)
                maxWidth(100.percent)
                position(Position.Relative)
                paddingBottom(8.px)
                lineHeight("1.57")
                whiteSpace("initial")
                textAlign("left")
            }
        }) {
            Label {
                Text("Номер телефона")
            }
        }

        val phoneInput = remember { mutableStateOf("+998 __ ___ __") }
        Div(attrs = {
            style {
                width(100.percent)
                position(Position.Relative)
                minHeight(48.px)
                maxWidth(100.percent)
                alignItems(AlignItems.Center)
            }
        }) {
            Input(type = InputType.Text) {
                style {
                    flex("auto")
                    width(100.percent)
                    border(width = 1.px, LineStyle.Solid, Color("#D0D5DD"))
                    boxSizing("border-box")
                    paddingLeft(14.px)
                    paddingRight(14.px)
                    paddingTop(12.px)
                    paddingBottom(12.px)
                    borderRadius(8.px)
                }
                placeholder("+998 __ ___ __ __") // optional
                onInput { event -> println(event.value) }
            }

        }

    }
}

@Composable
fun InputPassword() {
    Div(attrs = {
        style {
            boxSizing("border-box")
            margin(0.px)
            padding(0.px)
            property("vertical-align", "top")
            fontSize(14.px)
            alignContent(AlignContent.Start)
            paddingBottom(16.px)

        }
    }) {
        Div(attrs = {
            style {
                display(InlineBlock)
                maxWidth(100.percent)
                position(Position.Relative)
                paddingBottom(8.px)
                lineHeight("1.57")
                whiteSpace("initial")
                textAlign("left")
            }
        }) {
            Label {
                Text("Пароль")
            }
        }

        val phoneInput = remember { mutableStateOf("+998 __ ___ __") }
        Div(attrs = {
            style {
                width(100.percent)
                position(Position.Relative)
                minHeight(48.px)
                maxWidth(100.percent)
                alignItems(AlignItems.Center)
            }
        }) {
            Span(attrs = {
                style {

                }
            }) {
                Input(type = InputType.Password) {
                    style {
                        flex("auto")
                        width(100.percent)
                        border(width = 1.px, LineStyle.Solid, Color("#D0D5DD"))
                        boxSizing("border-box")
                        paddingLeft(14.px)
                        paddingRight(14.px)
                        paddingTop(12.px)
                        paddingBottom(12.px)
                        borderRadius(8.px)
                    }
                    placeholder("Введите пароль") // optional
                    onInput { event -> println(event.value) }
                }
            }
        }

    }
}

@Composable
private fun LoginSubtitle() {
    P(attrs = {
        style {
            color(Color("#1D2939"))
            fontFamily("Roboto", "Helvetica", "Arial", "sans-serif")
            fontWeight("lighter")
            fontSize(14.px)
            display(Flex)
            justifyContent(JustifyContent.Center)
        }
    }) {
        Text("Как вы хотите войти в систему?")
    }
}

@Composable
private fun LoginTitle() {
    H1(
        attrs = {
            style {
                color(Color("#1D2939"))
                fontFamily("Roboto", "Helvetica", "Arial", "sans-serif")
                fontSize(36.px)
                fontWeight(800)
                textAlign("center")
            }
        }) {
        Text("Авторизация")
    }
}

@Composable
fun TabSwitch(selectedOne: MutableState<Boolean>) {

    Div(attrs = {
        style {
            gap(6.px)
            borderRadius(8.px)
            display(DisplayStyle.Grid)
            background("#F9FAFB")
            padding(6.px)
            gridTemplateColumns("1fr 1fr")
        }
    }) {

        Div(attrs = {
            onClick {
                selectedOne.value = true
            }
            style {
                tabItemStyle(selectedOne.value)
            }

        }) {
            Text("По логину и паролю")
        }


        Div(attrs = {
            onClick {
                selectedOne.value = false
            }
            style {
                tabItemStyle(!selectedOne.value)
            }
        }) {
            Text("По ключу ЭЦП")
        }
    }
}

fun StyleScope.tabItemStyle(isSelected: Boolean) {
    display(Flex)
    justifyContent(JustifyContent.Center)
    if (isSelected) selectedTabItemStyle()
    else notSelectedTabItemStyle()
    fontFamily("Roboto", "Helvetica", "Arial", "sans-serif")
    paddingTop(10.px)
    paddingBottom(10.px)
    paddingLeft(22.px)
    paddingRight(22.px)
    borderRadius(8.px)
    alignItems(AlignItems.Center)
    fontSize(16.px)
    cursor("pointer")
    property("transition", "ease-in 0.1s")
}

fun StyleScope.selectedTabItemStyle() {
    color(Color("#1D2939"))
    fontWeight(600)
    background("#ffffff")
    property("box-shadow", "0px 1px 2px rgb(29 27 51 / 5%)")
}

fun StyleScope.notSelectedTabItemStyle() {
    color(Color("#667085"))
    background("00FFFFFF")
}
