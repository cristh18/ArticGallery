package com.tolodev.artic_gallery.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tolodev.artic_gallery.R

@Composable
fun DisplayImageWithCustomLoadingIndicator(
    modifier: Modifier,
    url: String,
    contentDescription: String
) {
    var isLoading by remember { mutableStateOf(true) }
    var loadError by remember { mutableStateOf(false) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        error = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
//        modifier = Modifier.clip(CircleShape),
        modifier = modifier,
        onLoading = { isLoading = true },
        onSuccess = { isLoading = false },
        onError = {
            isLoading = false
            loadError = true
        }
    )

    when {
        isLoading -> CircularProgressIndicator()
        loadError -> Text("Failed to load image")
    }
}