package com.deni.hilhamsyah.cookhub.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.deni.hilhamsyah.cookhub.R

private val poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight(200)),
    Font(R.font.poppins_regular,FontWeight(300)),
    Font(R.font.poppins_medium,FontWeight(400)),
    Font(R.font.poppins_semibold, FontWeight(500)),
    Font(R.font.poppins_bold, FontWeight(600)),
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
    ),
    bodySmall = TextStyle(
        fontFamily = poppins,
    ),
    labelLarge = TextStyle(
        fontFamily = poppins,
    ),
    labelMedium = TextStyle(
        fontFamily = poppins,
    ),
    labelSmall = TextStyle(
        fontFamily = poppins,
    )
)