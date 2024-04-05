package com.tolodev.artic_gallery.ui.components.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.components.style.caption2
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.theme.DeepTeal
import com.tolodev.artic_gallery.ui.theme.PaleCyan
import timber.log.Timber

@Composable
fun HomeArtworkItem(uiArtwork: UIArtwork, showArtworkDetail: (Long) -> Unit) {
    Timber.d(
        "ArtworkListItem", "Title: ${uiArtwork.title}, Description: ${uiArtwork.description}"
    )
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.spacing_xxsmall))
            .fillMaxWidth()
            .clickable {
                Timber.e("Selected: " + uiArtwork.title)
                showArtworkDetail(uiArtwork.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = PaleCyan,
            contentColor = DeepTeal
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PaleCyan),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DisplayImageWithCustomLoadingIndicator(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.icon_xxhuge))
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_huge))),
                url = uiArtwork.images[ImageSize.TINY]?.imageUrl.orEmpty(),
                contentDescription = uiArtwork.thumbnailAltText
            )
            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacing_xxsmall),
                    bottom = dimensionResource(id = R.dimen.spacing_standard),
                    start = dimensionResource(id = R.dimen.spacing_standard),
                    end = dimensionResource(id = R.dimen.spacing_standard)
                ),
                text = uiArtwork.title,
                maxLines = 2,
                style = caption2.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = DeepTeal
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeArtworkItemPreview() {
    HomeArtworkItem(uiArtwork = DataProviderMock.getMockedUIArtworks[0], showArtworkDetail = {})
}