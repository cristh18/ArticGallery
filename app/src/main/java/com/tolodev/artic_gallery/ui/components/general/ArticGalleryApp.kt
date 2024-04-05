package com.tolodev.artic_gallery.ui.components.general

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tolodev.artic_gallery.ui.theme.VeryLightCyan

@Composable
fun ArticGalleryApp(content: @Composable (PaddingValues) -> Unit) {
//    ArticGalleryTheme {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = VeryLightCyan,
        content = {
            content(it)
        })
//    }
}