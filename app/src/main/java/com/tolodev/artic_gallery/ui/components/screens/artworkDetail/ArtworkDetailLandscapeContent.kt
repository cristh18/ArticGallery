package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.ui.models.UIArtwork

@Composable
fun ArtworkDetailLandscapeContent(uiArtwork: UIArtwork) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = dimensionResource(id = R.dimen.spacing_xhuge))
            .background(Color.White),
    ) {
        ArtworkImage(
            uiArtwork = uiArtwork,
            modifier = Modifier
                .fillMaxHeight()
                .padding(dimensionResource(id = R.dimen.spacing_large)),
            size = dimensionResource(id = R.dimen.max_width_chat),
            alignment = Alignment.CenterStart
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = dimensionResource(id = R.dimen.spacing_large))
                .background(Color.White),
        ) {

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
}

@Preview(
    name = "Landscape Mode",
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    showSystemUi = true,
)
@Preview(
    name = "Portrait Mode",
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true,
)
@Preview(
    name = "ArtworkDetailLandscapeContentPreview",
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape",
    showBackground = true
)
@PreviewScreenSizes
annotation class OrientationPreviews


@OrientationPreviews
@Composable
fun ArtworkDetailLandscapeContentPreview() {
    ArtworkDetailLandscapeContent(DataProviderMock.getMockedUIArtworks[0])
}