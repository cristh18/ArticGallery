package com.tolodev.artic_gallery.utils

import android.content.Context
import android.content.res.Configuration

fun isPortraitOrientation(context: Context): Boolean {
    return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}