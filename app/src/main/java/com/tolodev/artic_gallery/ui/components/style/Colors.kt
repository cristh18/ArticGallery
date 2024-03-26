package com.tolodev.artic_gallery.ui.components.style
import androidx.compose.ui.graphics.Color

const val PRIMARY_NEGATIVE = 0xFFED1515
const val PRIMARY_WARNING = 0xFFF65900
const val PRIMARY_POSITIVE = 0xFF049D85
const val PRIMARY_STREAK = 0xFF6601FF
const val PRIMARY_HIGHLIGHT = 0xFF1D6FD8
const val PASTEL_NEGATIVE = 0xFFFFE1DB
const val PASTEL_WARNING = 0xFFFFEEDB
const val PASTEL_POSITIVE = 0xFFDBFFE9
const val PASTEL_STREAK = 0xFFE1DCFF
const val PASTEL_HIGHLIGHT = 0xFFDBEAFE
const val CONTENT_A = 0xFFFFFFFF
const val CONTENT_B = 0xFF354360
const val CONTENT_C = 0xFF616F8E
const val CONTENT_D = 0xFF7E9EBC
const val CONTENT_E = 0xFF1C2233
const val BORDER_A = 0xFFC9D3E3
const val BORDER_B = 0xFFE8EFF6
const val BACKGROUND_A = 0xFFF3F5F9
const val BACKGROUND_B = 0xFFF8FAFC
const val BACKGROUND_C = 0xFFF0F4FA
const val GRADIENT_GREEN_A = 0xFF1FD8B5
const val GRADIENT_GREEN_B = 0xFF23AEB9
const val GRADIENT_PURPLE_A = 0xFF803AFF
const val GRADIENT_PURPLE_B = 0xFF5800DD
const val GRADIENT_GOLD_A = 0xFFFFCC17
const val GRADIENT_GOLD_B = 0xFFFF9900
const val OVERLAY = 0x80354360
const val BLUE_HOME = 0xFF136EF1
const val BLACK = 0xFF000000
const val COLOR_TIP = 0xFFDBEAFE
const val COLOR_TEXT_TIP = 0xFF1D6FD8

val color_primary_negative = Color(PRIMARY_NEGATIVE)
val color_primary_warning = Color(PRIMARY_WARNING)
val color_primary_positive = Color(PRIMARY_POSITIVE)
val color_primary_streak = Color(PRIMARY_STREAK)
val color_primary_highlight = Color(PRIMARY_HIGHLIGHT)
val color_pastel_negative = Color(PASTEL_NEGATIVE)
val color_pastel_warning = Color(PASTEL_WARNING)
val color_pastel_positive = Color(PASTEL_POSITIVE)
val color_pastel_streak = Color(PASTEL_STREAK)
val color_pastel_highlight = Color(PASTEL_HIGHLIGHT)
val color_content_A = Color(CONTENT_A)
val color_content_B = Color(CONTENT_B)
val color_content_C = Color(CONTENT_C)
val color_tips = Color(COLOR_TIP)
val color_text_tips = Color(COLOR_TEXT_TIP)
val color_content_D = Color(CONTENT_D)
val color_content_E = Color(CONTENT_E)
val color_border_A = Color(BORDER_A)
val color_border_B = Color(BORDER_B)
val color_background_A = Color(BACKGROUND_A)
val color_background_B = Color(BACKGROUND_B)
val color_background_C = Color(BACKGROUND_C)
val color_overlay = Color(OVERLAY)

val color_gradient_green_A = Color(GRADIENT_GREEN_A)
val color_gradient_green_B = Color(GRADIENT_GREEN_B)
val color_gradient_purple_A = Color(GRADIENT_PURPLE_A)
val color_gradient_purple_B = Color(GRADIENT_PURPLE_B)
val color_gradient_gold_A = Color(GRADIENT_GOLD_A)
val color_gradient_gold_B = Color(GRADIENT_GOLD_B)
val color_blue_home = Color(BLUE_HOME)
val color_black = Color(BLACK)

fun Color.setAlpha(alpha: Int) = this.copy(alpha = alpha.toFloat())
