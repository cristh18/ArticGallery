package com.tolodev.artic_gallery.extensions

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun Double?.orZero() = this ?: 0.0

fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0

fun Float?.orZero() = this ?: 0F
