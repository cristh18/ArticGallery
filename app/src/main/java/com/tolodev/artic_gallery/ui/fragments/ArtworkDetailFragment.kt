package com.tolodev.artic_gallery.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.extensions.composeView
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.components.style.body
import com.tolodev.artic_gallery.ui.components.style.caption2
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme
import com.tolodev.artic_gallery.ui.theme.DeepTeal
import com.tolodev.artic_gallery.ui.theme.PaleCyan
import com.tolodev.artic_gallery.ui.theme.VeryLightCyan
import com.tolodev.artic_gallery.ui.viewModels.ArtworkDetailViewModel
import com.tolodev.artic_gallery.utils.bundleToMap
import com.tolodev.artic_gallery.utils.mapToBundle
import com.tolodev.artic_gallery.utils.startDestination
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment() {

    private var modelDataView = mutableStateListOf<UIStatus<UIArtwork>>()

    private val viewModel by viewModels<ArtworkDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return composeView {
            if (modelDataView.isNotEmpty()) {
                ArtworkComponent(uiStatus = modelDataView.last())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            val args = ArtworkDetailFragmentArgs.fromBundle(bundle)
            args.artworkId.let { artworkId ->
                args.flow.let { flow ->
                    viewModel.initViewModel(artworkId, flow)
                    subscribe()
                    Timber.e("artworkId: $artworkId - flow: $flow")
                }
            }
        }
    }

    private fun subscribe() {
        with(viewModel) {
            uiStatusObserver().observe(viewLifecycleOwner, ::setupView)
            navigateObserver().observe(viewLifecycleOwner) {
                showHome()
            }
        }
    }

    private fun setupView(uiStatus: UIStatus<UIArtwork>) {
        modelDataView.clear()
        modelDataView.add(uiStatus)
    }

    private fun showHome() {
        if (viewModel.isFavoriteFlow()) {
            startDestination(
                ArtworkDetailFragmentDirections.actionArtworkDetailFragmentToFavoriteArtworksFragment(),
                this
            )
        } else {
            startDestination(
                ArtworkDetailFragmentDirections.actionArtworkDetailFragmentToHomeFragment(), this
            )
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putAll(mapToBundle(viewModel.setInstanceState()))
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        savedInstanceState?.let { viewModel.restoreInstanceState(bundleToMap(it)) }
        super.onViewStateRestored(savedInstanceState)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun ArtworkComponent(uiStatus: UIStatus<UIArtwork>) {
        if (uiStatus is UIStatus.Successful) {
            val uiArtwork: UIArtwork = uiStatus.value
            Scaffold(modifier = Modifier.fillMaxSize(), contentColor = VeryLightCyan, topBar = {
                ArtworkDetailTopBar(uiArtwork)
            }, content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 80.dp)
                        .background(Color.White),
                ) {
                    item {
                        ArtworkImage(uiArtwork)
                    }

                    item {
                        ArtworkDetailsItem(uiArtwork)
                    }
                    if (uiArtwork.description.isNotEmpty()) {
                        item {
                            ArtworkDescriptionItem(uiArtwork)
                        }
                    }
                }
            })
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun ArtworkDetailTopBar(
        uiArtwork: UIArtwork
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
                    .size(24.dp)
                    .clickable { viewModel.saveFavoriteArtwork(uiArtwork.id) },
                model = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border,
                contentDescription = stringResource(id = R.string.copy_save)
            )
        })
    }

    @Composable
    private fun ArtworkImage(uiArtwork: UIArtwork) {
        Box(modifier = Modifier.fillMaxWidth()) {
            val modifier: Modifier = Modifier
                .size(350.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(16.dp))
            DisplayImageWithCustomLoadingIndicator(
                modifier = modifier,
                url = uiArtwork.images[ImageSize.BIG]?.imageUrl.orEmpty(),
                contentDescription = uiArtwork.thumbnailAltText
            )
        }
    }

    @Composable
    private fun ArtworkDetailsItem(uiArtwork: UIArtwork) {
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

            val items = viewModel.getArtworkDetails(uiArtwork, requireContext())

            if (expanded) {
                items.forEach { item ->

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

    @Composable
    private fun ArtworkDescriptionItem(uiArtwork: UIArtwork) {
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
                    contentDescription = stringResource(id = R.string.copy_description),
                    tint = DeepTeal
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
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
                        top = 8.dp, start = 48.dp, end = 16.dp
                    ),
                    text = descriptionText,
                    style = caption2.copy(color = DeepTeal),
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ArtworkComponentPreview() {
        ArticGalleryTheme {
            ArtworkComponent(UIStatus.Successful(value = DataProviderMock.getMockedUIArtworks[0]))
        }
    }
}