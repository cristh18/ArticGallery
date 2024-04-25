package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.models.UIArtwork

@Composable
fun ArtworkImage(
    uiArtwork: UIArtwork,
    modifier: Modifier,
    size: Dp,
    alignment: Alignment
) {
    Box(modifier = modifier) {
        val imageModifier: Modifier = Modifier
            .size(size)
            .align(alignment)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_large)))
        DisplayImageWithCustomLoadingIndicator(
            modifier = imageModifier,
            url = uiArtwork.images[ImageSize.BIG]?.imageUrl.orEmpty(),
            contentDescription = uiArtwork.thumbnailAltText
        )
    }
}