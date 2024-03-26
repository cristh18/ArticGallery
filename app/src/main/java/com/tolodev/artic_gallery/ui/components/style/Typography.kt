package com.tolodev.artic_gallery.ui.components.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.tolodev.artic_gallery.R

val defaultFontFamily: FontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semibold, FontWeight.SemiBold)
)

/*** font styles Design System ***/

val headLine: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 32.sp,
    color = color_content_B,
    fontWeight = FontWeight.Normal
)

val headLine2: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 24.sp,
    color = color_content_B,
    fontWeight = FontWeight.Normal,
)

val headLine1: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 20.sp,
    color = color_content_B,
    fontWeight = FontWeight.Normal,
)

val body: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 16.sp,
    color = color_content_B,
    fontWeight = FontWeight.Normal
)

val caption2: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 14.sp,
    color = color_content_B,
    fontWeight = FontWeight.Normal
)

val caption1: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 12.sp,
    color = color_content_B,
    fontWeight = FontWeight.Normal
)

val label: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 10.sp,
    color = color_content_B,
    fontWeight = FontWeight.Normal
)

val subhead: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 10.sp,
    color = color_content_C,
    fontWeight = FontWeight.Normal
)

fun TextStyle.semiBold(color: Color = this.color) = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = this.fontSize,
    color = color,
    fontWeight = FontWeight.SemiBold
)

fun TextStyle.underline() = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = this.fontSize,
    color = this.color,
    fontWeight = this.fontWeight,
    textDecoration = TextDecoration.Underline
)

fun TextStyle.center(color: Color = this.color) = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = this.fontSize,
    color = color,
    fontWeight = this.fontWeight,
    textAlign = TextAlign.Center
)

fun TextStyle.color(color: Color) = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = this.fontSize,
    color = color,
    fontWeight = this.fontWeight,
    textDecoration = this.textDecoration
)

val TextBodySemibold: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontSize = 16.sp,
    color = color_content_B,
    fontWeight = FontWeight.SemiBold
)

val NumberTipCode: TextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    color = color_text_tips,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.SemiBold
)
