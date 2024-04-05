package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.models.UIArtwork

@Composable
fun ArtworkImage(uiArtwork: UIArtwork) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val modifier: Modifier = Modifier
            .size(350.dp)
            .align(Alignment.Center)
            .clip(RoundedCornerShape(16.dp))
        DisplayImageWithCustomLoadingIndicator(
            modifier = modifier,
            url = uiArtwork.images[ImageSize.BIG]?.imageUrl.orEmpty(),
            contentDescription = uiArtwork.thumbnailAltText
        )
    }
}