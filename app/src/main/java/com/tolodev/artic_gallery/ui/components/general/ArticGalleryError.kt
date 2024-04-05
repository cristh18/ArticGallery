package com.tolodev.artic_gallery.ui.components.general

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.ui.components.style.body
import com.tolodev.artic_gallery.ui.components.style.color_primary_warning
import com.tolodev.artic_gallery.ui.theme.VeryLightCyan

@Composable
fun ArticGalleryError(errorMessage: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.spacing_huge))
            .background(VeryLightCyan),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_favorite_filled),
            contentDescription = stringResource(id = R.string.copy_error),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.spacing_large))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            style = body.copy(
                color = color_primary_warning
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.spacing_large)),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticGalleryErrorPreview() {
    ArticGalleryError("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
}