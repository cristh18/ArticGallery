package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.ui.components.general.ArticGalleryApp
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.utils.isPortraitOrientation

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
            if (isPortraitOrientation(context = LocalContext.current)) {
                ArtworkDetailPortraitContent(uiArtwork)
            } else {
                ArtworkDetailLandscapeContent(uiArtwork)
            }
        }
    )
}

@Preview(showBackground = true)
@PreviewScreenSizes
@Composable
private fun ArtworkDetailScreenPreview() {
    ArtworkDetailScreen(DataProviderMock.getMockedUIArtworks[0])
}