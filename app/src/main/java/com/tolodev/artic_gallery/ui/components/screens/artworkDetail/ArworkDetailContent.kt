package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.ui.models.UIArtwork

@Composable
fun ArworkDetailContent(uiArtwork: UIArtwork) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = dimensionResource(id = R.dimen.width_weight_option))
            .background(Color.White),
    ) {
        item {
            ArtworkImage(uiArtwork)
        }

        item {
            ArtworkDetailsItem(uiArtwork)
        }
        if (uiArtwork.description.isNotEmpty()) {
            item {
                ArtworkDescriptionItem(uiArtwork)
            }
        }
    }
}