package com.tolodev.artic_gallery.ui.components.screens.artworkDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.ui.components.style.body
import com.tolodev.artic_gallery.ui.components.style.caption2
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.theme.DeepTeal

@Composable
fun ArtworkDescriptionItem(uiArtwork: UIArtwork) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.spacing_huge))
                .background(Color.LightGray.copy(alpha = 0.2f)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_huge)))
            Icon(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.spacing_xxlarge))
                    .clickable { expanded = !expanded },
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.copy_description),
                tint = DeepTeal
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_standard)))
            Text(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.spacing_large),
                        bottom = dimensionResource(id = R.dimen.spacing_large)
                    )
                    .clickable { expanded = !expanded },
                text = stringResource(id = R.string.copy_description),
                style = body.copy(
                    fontWeight = FontWeight.Bold, color = DeepTeal
                ),
                textAlign = TextAlign.Start
            )
        }

        if (expanded) {
            val descriptionText = uiArtwork.description.replace("<p>", "")
                .replace("</p>", "")
            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacing_standard),
                    start = dimensionResource(id = R.dimen.spacing_xxhuge),
                    end = dimensionResource(id = R.dimen.spacing_large)
                ),
                text = descriptionText,
                style = caption2.copy(color = DeepTeal),
            )
        }
    }
}