package com.tolodev.artic_gallery.ui.components.screens.favoriteArtworks

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.theme.DeepTeal
import com.tolodev.artic_gallery.ui.theme.VeryLightCyan

@Composable
fun FavoriteArtworkList(
    artworks: List<UIArtwork>,
    showArtworkDetail: (Long) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.background(VeryLightCyan),
    ) {
        items(artworks.size) { index ->
            FavoriteArtworkItem(
                artworks[index],
                showArtworkDetail = { showArtworkDetail(it) })
            HorizontalDivider(
                color = DeepTeal,
                thickness = dimensionResource(id = R.dimen.spacing_xmicro)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteArtworkListPreview() {
    FavoriteArtworkList(artworks = DataProviderMock.getMockedUIArtworks)
}