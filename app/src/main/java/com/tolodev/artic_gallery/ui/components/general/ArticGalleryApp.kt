package com.tolodev.artic_gallery.ui.components.general

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme
import com.tolodev.artic_gallery.ui.theme.VeryLightCyan

@Composable
fun ArticGalleryApp(
    topBar: @Composable () -> Unit = { },
    content: @Composable (PaddingValues) -> Unit
) {
    ArticGalleryTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentColor = VeryLightCyan,
            topBar = {
                topBar()
            },
            content = {
                content(it)
            })
    }
}