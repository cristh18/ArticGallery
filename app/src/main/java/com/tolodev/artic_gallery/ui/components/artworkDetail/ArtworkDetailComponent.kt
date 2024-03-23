package com.tolodev.artic_gallery.ui.components.artworkDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArtworkComponent(uiStatus: UIStatus<Artwork>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        content = {
            if (uiStatus is UIStatus.Successful) {
                val artwork: Artwork = uiStatus.value
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        text = artwork.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    val modifier: Modifier = Modifier
                        .size(400.dp)
                        .fillMaxWidth()
                    DisplayImageWithCustomLoadingIndicator(
                        modifier = modifier,
                        url = artwork.getImages()[ImageSize.BIG]?.imageUrl.orEmpty(),
                        contentDescription = artwork.thumbnailAltText
                    )

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Description",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        fontWeight = FontWeight(1000),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        text = artwork.description,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ArtworkComponentPreview() {
    ArticGalleryTheme {
        ArtworkComponent(UIStatus.Successful(value = DataProviderMock.getMockedArtworks[0]))
    }
}