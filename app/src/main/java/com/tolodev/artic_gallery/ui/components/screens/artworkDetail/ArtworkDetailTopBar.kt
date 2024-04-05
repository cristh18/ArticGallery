package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.ui.components.style.body
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.theme.DeepTeal
import com.tolodev.artic_gallery.ui.theme.PaleCyan

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ArtworkDetailTopBar(
    uiArtwork: UIArtwork,
    showHome: () -> Unit = {},
    saveFavoriteArtwork: (Long) -> Unit = {}
) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = PaleCyan,
        titleContentColor = DeepTeal,
    ), title = {
        Text(
            text = uiArtwork.title,
            style = body.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center
        )
    }, navigationIcon = {
        IconButton(onClick = { showHome() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Localized description"
            )
        }
    }, actions = {
        val isFavorite = uiArtwork.isFavorite
        AsyncImage(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.spacing_xxlarge))
                .clickable { saveFavoriteArtwork(uiArtwork.id) },
            model = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border,
            contentDescription = stringResource(id = R.string.copy_save)
        )
    })
}