package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.ui.components.general.ArticGalleryApp
import com.tolodev.artic_gallery.ui.models.UIArtwork

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArtworkDetailScreen(
    uiArtwork: UIArtwork,
    primaryAction: () -> Unit,
    secondaryAction: (Long) -> Unit
) {
    ArticGalleryApp(
        topBar = {
            ArtworkDetailTopBar(
                uiArtwork,
                showHome = { primaryAction() },
                saveFavoriteArtwork = { secondaryAction(it) })
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 80.dp)
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
    )
}

@Preview(showBackground = true)
@Composable
private fun ArtworkDetailScreenPreview() {
    ArtworkDetailScreen(DataProviderMock.getMockedUIArtworks[0], {}, {})
}