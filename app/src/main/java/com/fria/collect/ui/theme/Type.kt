package com.fria.collect.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fria.collect.R

// Set of Material typography styles to start with
val Typography: Typography
    get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = GmarketFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        )
    )

val GmarketFont = FontFamily(
    Font(R.font.gmarket_sans_medium, FontWeight.Normal),
    Font(R.font.gmarket_sans_light,FontWeight.Light),
    Font(R.font.gmarket_sans_bold, FontWeight.Bold),
)