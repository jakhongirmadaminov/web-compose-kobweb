import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun apayUzHome() {
    Div {
        Img("logo.png", attrs = {
            style {
                alignContent(AlignContent.Start)
                paddingTop(50.px)
                paddingLeft(150.px)
            }
        }
        )

        Table({
            style {
                flexDirection(FlexDirection.Row)
                paddingLeft(150.px)
                paddingRight(150.px)
            }
        }) {
            Tr {
                Td(attrs = {
                    style {
                        width(40.percent)
                        paddingRight(10.px)
                    }
                }) {
                    Img("img_phones.png") {
                        style { width(100.percent) }
                    }
                }

                Td(attrs = {
                    style {
                        paddingLeft(10.px)
                        width(40.percent)
                    }
                }) {
                    H2(
                        attrs = {
                            style {
                                color(Color("#03005b"))
                                fontFamily("sans-serif")
                                fontSize(50.px)
                            }
                        }) {
                        Text(
                            "Yangi usulda pul toʻlang, oʻtkazing\n" +
                                    "va yigʻing."
                        )
                    }
                    P(attrs = {
                        style {
                            color(Color.black)
                            fontSize(22.px)
                            fontFamily("sans-serif")
                        }
                    }) {
                        Text(
                            "Oʻzbekiston boʻylab 30 000 ta savdo nuqtalarida odatiy harakatlar uchun bonuslar va barcha toʻlovlar uchun 2%dan boshlanadigan keshbekka ega boʻling.\n" +
                                    "Bonuslarni ilovaning Peshtaxtasidagi gadjetlar va merchlarga almashing. Komissiyasiz pul oʻtkazing. Yanada qulayroq toʻlash uchun bank kartalarini A-Pay Karta bilan bogʻlang."
                        )
                    }
                    Img(
                        "download_google_play.png",
                        attrs = {
                            style {
                                alignContent(AlignContent.Baseline)
                            }
                        }
                    )
                }

            }


        }
    }
}