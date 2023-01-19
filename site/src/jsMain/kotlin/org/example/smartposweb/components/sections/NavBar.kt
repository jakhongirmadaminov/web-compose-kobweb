package org.example.smartposweb.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.AlignSelf
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.style.*
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.rememberColorMode
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import com.varabyte.kobweb.silk.theme.toSilkPalette
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*

val NavBarStyleShrunk = ComponentStyle.base("nav-bar-shrunk") {
    Modifier
        .fillMaxHeight()
        .width(50.px)
        // Intentionally invert the header colors from the rest of the page
        .backgroundColor(colorMode.toSilkPalette().color)
}
val NavBarStyleExpanded = ComponentStyle.base("nav-bar-expanded") {
    Modifier
        .fillMaxHeight()
        .width(150.px)
        // Intentionally invert the header colors from the rest of the page
        .backgroundColor(colorMode.toSilkPalette().color)
}

val NavItemStyle = ComponentStyle("nav-item") {
    // Intentionally invert the header colors from the rest of the page
    val linkColor = colorMode.toSilkPalette().background

    base { Modifier.margin(leftRight = 15.px) }

    link { Modifier.color(linkColor) }
    visited { Modifier.color(linkColor) }
}

val NavButtonVariant = NavItemStyle.addVariant("button") {
    base { Modifier.padding(0.px).clip(Circle()) }
}

@Composable
private fun NavLink(path: String, text: String) {
    Link(path, text, NavItemStyle.toModifier(), UndecoratedLinkVariant)
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun NavBar() {
    var colorMode by rememberColorMode()
    var navBarStyle by remember { mutableStateOf(NavBarStyleShrunk) }

    Box(navBarStyle.toModifier()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavLink("/home", "HOME")
//            NavLink("/", "HOME")
//            NavLink("/about", "ABOUT")
//            NavLink("/markdown", "MARKDOWN")
//            NavLink("/smartposadminlogin", "MARKDOWN2")
            Spacer()

            Box(
                modifier = Modifier.backgroundColor(Color.red).height(50.px)
                    .width(if (navBarStyle == NavBarStyleExpanded) 150.px else 50.px)
                    .display(DisplayStyle.Flex)
                    .justifyContent(JustifyContent.Center)
                    .alignItems(AlignItems.Center)
                    .onClick {
                        navBarStyle = if (navBarStyle == NavBarStyleExpanded) {
                            NavBarStyleShrunk
                        } else {
                            NavBarStyleExpanded
                        }
                    }) {
                Image(
                    "right-arrow.png",
                    modifier = Modifier.fillMaxWidth().backgroundColor(Color.white)
                        .size(40.px).transform {
                            scaleX(if (navBarStyle == NavBarStyleExpanded) -1 else 1)
                        }
                )
            }

            Button(
                onClick = { colorMode = colorMode.opposite() },
                NavItemStyle.toModifier(NavButtonVariant)
            ) {
                Box(Modifier.fillMaxWidth().align(Alignment.Center)) {
                    when (colorMode) {
                        ColorMode.LIGHT -> FaMoon()
                        ColorMode.DARK -> FaSun()
                    }
                }
            }
        }
    }
}