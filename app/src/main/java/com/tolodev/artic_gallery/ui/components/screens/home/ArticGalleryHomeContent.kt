package com.tolodev.artic_gallery.ui.components.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tolodev.artic_gallery.ui.models.UIArtwork

@Composable
fun ArticGalleryHomeContent(
    paddingValues: PaddingValues,
    artworks: List<UIArtwork>,
    showArtworkDetail: (Long) -> Unit
) {
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = paddingValues
    ) {
        items(artworks) { artwork ->
            ArtworkListItem(uiArtwork = artwork, showArtworkDetail = { showArtworkDetail(it) })
        }
    }
}