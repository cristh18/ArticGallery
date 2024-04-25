package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.ui.models.UIArtwork

@Composable
fun ArtworkDetailPortraitContent(uiArtwork: UIArtwork) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = dimensionResource(id = R.dimen.width_weight_option))
            .background(Color.White),
    ) {
        item {
            ArtworkImage(
                uiArtwork = uiArtwork,
                modifier = Modifier.fillMaxWidth(),
                size = dimensionResource(id = R.dimen.max_height_list_notify_orders),
                alignment = Alignment.Center
            )
        }
        if (uiArtwork.description.isNotEmpty()) {
            item {
                ArtworkDescriptionItem(uiArtwork)
            }
        }
        item {
            ArtworkDetailsItem(uiArtwork)
        }
    }
}