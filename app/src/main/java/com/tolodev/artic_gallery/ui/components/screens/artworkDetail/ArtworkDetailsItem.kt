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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.ui.components.style.body
import com.tolodev.artic_gallery.ui.components.style.caption2
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.theme.DeepTeal

@Composable
fun ArtworkDetailsItem(uiArtwork: UIArtwork) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .background(Color.LightGray.copy(alpha = 0.2f)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(32.dp))
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { expanded = !expanded },
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.copy_artwork_details),
                tint = DeepTeal
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .clickable { expanded = !expanded },
                text = stringResource(id = R.string.copy_artwork_details),
                style = body.copy(
                    fontWeight = FontWeight.Bold, color = DeepTeal
                ),
            )
        }

        if (expanded) {

            val context = LocalContext.current
            uiArtwork.getArtworkDetails(context).forEach { item ->

                val customizedText = buildAnnotatedString {
                    val boldIndex = item.indexOf(":") + 1
                    val boldText = item.substring(0, boldIndex)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(boldText)
                    }
                    val normalText = item.substring(boldIndex, item.length)
                    append(" $normalText")
                }

                Text(
                    modifier = Modifier.padding(
                        top = 8.dp, start = 48.dp, end = 16.dp
                    ),
                    text = customizedText,
                    style = caption2.copy(color = DeepTeal),
                )
            }
        }
    }
}