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
import androidx.compose.ui.res.dimensionResource
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.ui.models.UIArtwork

@Composable
fun HomeArtworkList(
    paddingValues: PaddingValues,
    artworks: List<UIArtwork>,
    showArtworkDetail: (Long) -> Unit
) {
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = dimensionResource(id = R.dimen.spacing_xxsmall),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_xxsmall)),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = dimensionResource(id = R.dimen.spacing_huge),
                bottom = dimensionResource(id = R.dimen.spacing_large),
                start = dimensionResource(id = R.dimen.spacing_large),
                end = dimensionResource(id = R.dimen.spacing_large)
            ),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = paddingValues
    ) {
        items(artworks) { artwork ->
            HomeArtworkListItem(uiArtwork = artwork, showArtworkDetail = { showArtworkDetail(it) })
        }
    }
}