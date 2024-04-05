package com.tolodev.artic_gallery.ui.components.screens.favoriteArtworks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.components.style.headLine1
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.theme.DeepTeal
import com.tolodev.artic_gallery.ui.theme.VeryLightCyan
import timber.log.Timber

@Composable
fun FavoriteArtworkItem(uiArtwork: UIArtwork, showArtworkDetail: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                top = dimensionResource(id = R.dimen.spacing_huge),
                bottom = dimensionResource(id = R.dimen.spacing_large),
                start = dimensionResource(id = R.dimen.spacing_huge),
                end = dimensionResource(id = R.dimen.spacing_huge)
            )
            .background(VeryLightCyan)
            .clickable {
                Timber.e("Selected: " + uiArtwork.title)
                showArtworkDetail(uiArtwork.id)
            }
    ) {
        Text(
            modifier = Modifier
                .weight(0.67f)
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
            text = uiArtwork.title,
            textAlign = TextAlign.Center,
            style = headLine1.copy(
                fontWeight = FontWeight.Bold,
                color = DeepTeal
            )
        )
        Column {
            DisplayImageWithCustomLoadingIndicator(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.descriptions_text))
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_large))),
                url = uiArtwork.images[ImageSize.TINY]?.imageUrl.orEmpty(),
                contentDescription = uiArtwork.thumbnailAltText
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = stringResource(id = R.string.copy_share),
                        tint = DeepTeal
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.copy_delete),
                        tint = DeepTeal
                    )
                }
            }
        }
    }
}