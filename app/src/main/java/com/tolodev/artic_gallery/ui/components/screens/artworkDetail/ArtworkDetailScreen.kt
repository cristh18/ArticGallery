package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.ui.components.general.ArticGalleryApp
import com.tolodev.artic_gallery.ui.models.UIArtwork

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArtworkDetailScreen(
    uiArtwork: UIArtwork,
    primaryAction: () -> Unit = {},
    secondaryAction: (Long) -> Unit = {}
) {
    ArticGalleryApp(
        topBar = {
            ArtworkDetailTopBar(
                uiArtwork,
                showHome = { primaryAction() },
                saveFavoriteArtwork = { secondaryAction(it) })
        },
        content = {
            ArworkDetailContent(uiArtwork)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ArtworkDetailScreenPreview() {
    ArtworkDetailScreen(DataProviderMock.getMockedUIArtworks[0])
}