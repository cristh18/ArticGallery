package com.tolodev.artic_gallery.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.domain.models.DataProviderMock.getMockedArtworks
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme
import timber.log.Timber

@Composable
fun HomeComponent(uiStatus: UIStatus<List<Artwork>>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        content = {
            when (uiStatus) {
                is UIStatus.Loading -> ArticGalleryLoader()
                is UIStatus.Successful -> ArticGalleryHomeContent(
                    paddingValues = it,
                    uiStatus.value
                )

                is UIStatus.Error -> {
                    Timber.e("Error: ${uiStatus.msg}")
                    Text(text = uiStatus.msg)
                }
            }
        }
    )
}

@Composable
fun ArticGalleryHomeContent(paddingValues: PaddingValues, artworks: List<Artwork>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp),
        contentPadding = paddingValues
    ) {
        items(artworks.size) { index ->
            ArtworkListItem(artworks[index])
        }
    }
}

@Composable
fun ArtworkListItem(artwork: Artwork) {
    Timber.d("ArtworkListItem", "Title: ${artwork.title}, Description: ${artwork.description}")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = artwork.title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Text(
            text = artwork.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Composable
fun ArticGalleryLoader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ArticGalleryTheme {
        ArticGalleryLoader()
        HomeComponent(UIStatus.Successful(value = getMockedArtworks))
    }
}