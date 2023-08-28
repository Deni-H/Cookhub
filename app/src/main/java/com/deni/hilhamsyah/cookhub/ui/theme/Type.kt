package com.deni.hilhamsyah.cookhub.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.deni.hilhamsyah.cookhub.R

private val poppins = FontFamily(
    Font(R.font.poppins_thin, FontWeight(100)),
    Font(R.font.poppins_light, FontWeight(200)),
    Font(R.font.poppins_extra_light, FontWeight(300)),
    Font(R.font.poppins_regular, FontWeight(400)),
    Font(R.font.poppins_medium, FontWeight(500)),
    Font(R.font.poppins_semi_bold, FontWeight(600)),
    Font(R.font.poppins_bold, FontWeight(700)),
    Font(R.font.poppins_extra_bold, FontWeight(800)),
    Font(R.font.poppins_black, FontWeight(900)),

    Font(R.font.poppins_thin_italic, FontWeight(100), FontStyle.Italic),
    Font(R.font.poppins_light_italic, FontWeight(200), FontStyle.Italic),
    Font(R.font.poppins_extra_light_italic, FontWeight(300), FontStyle.Italic),
    Font(R.font.poppins_italic, FontWeight(400), FontStyle.Italic),
    Font(R.font.poppins_medium_italic, FontWeight(500), FontStyle.Italic),
    Font(R.font.poppins_semi_bold_italic, FontWeight(600), FontStyle.Italic),
    Font(R.font.poppins_bold_italic, FontWeight(700), FontStyle.Italic),
    Font(R.font.poppins_extra_bold_italic, FontWeight(800), FontStyle.Italic),
    Font(R.font.poppins_black_italic, FontWeight(900), FontStyle.Italic),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = poppins
    ),
    displayMedium = TextStyle(
        fontFamily = poppins,
    ),
    displaySmall = TextStyle(
        fontFamily = poppins,
    ),
    headlineLarge = TextStyle(
        fontFamily = poppins,
        fontSize = 56.sp,
        lineHeight = 67.2.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = poppins,
    ),
    headlineSmall = TextStyle(
        fontFamily = poppins,
    ),
    titleLarge = TextStyle(
        fontFamily = poppins,
    ),
    titleMedium = TextStyle(
        fontFamily = poppins,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
        fontWeight = FontWeight(600),
    ),
    titleSmall = TextStyle(
        fontFamily = poppins,
    ),
    bodyLarge = TextStyle(
        fontFamily = poppins,
    ),
    bodyMedium = TextStyle(
        fontFamily = poppins,
        fontSize = 16.sp,
        lineHeight = 22.4.sp,
        fontWeight = FontWeight(400),
    ),
    bodySmall = TextStyle(
        fontFamily = poppins,
        fontSize = 12.sp,
        fontWeight = FontWeight(500),
    ),
    labelLarge = TextStyle(
        fontFamily = poppins,
    ),
    labelMedium = TextStyle(
        fontFamily = poppins,
    ),
    labelSmall = TextStyle(
        fontFamily = poppins,
        fontSize = 12.sp
    )
)