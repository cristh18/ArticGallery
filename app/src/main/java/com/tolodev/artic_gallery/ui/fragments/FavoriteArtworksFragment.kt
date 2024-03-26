package com.tolodev.artic_gallery.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.extensions.composeView
import com.tolodev.artic_gallery.ui.ArtworkFlow
import com.tolodev.artic_gallery.ui.activities.MainActivity
import com.tolodev.artic_gallery.ui.components.ArticGalleryLoader
import com.tolodev.artic_gallery.ui.components.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.components.style.headLine1
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme
import com.tolodev.artic_gallery.ui.theme.DeepTeal
import com.tolodev.artic_gallery.ui.theme.VeryLightCyan
import com.tolodev.artic_gallery.ui.viewModels.FavoriteArtworksViewModel
import com.tolodev.artic_gallery.utils.startDestination
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FavoriteArtworksFragment : Fragment() {

    private var modelDataView = mutableStateListOf<UIStatus<List<UIArtwork>>>()

    private val viewModel by viewModels<FavoriteArtworksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).showBottomNavigationView()
        return composeView {
            modelDataView.forEach { model ->
                FavoriteArtworksComponent(model)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initViewModel()
        subscribe()
    }

    private fun subscribe() {
        viewModel.uiStatusObserver().observe(viewLifecycleOwner, ::setupView)
    }

    private fun setupView(uiStatus: UIStatus<List<UIArtwork>>) {
        modelDataView.clear()
        modelDataView.addAll(listOf(uiStatus))
    }

    private fun showArtworkDetail(artwordId: Long) {
        (requireActivity() as MainActivity).hideBottomNavigationView()
        startDestination(
            FavoriteArtworksFragmentDirections.actionFavoriteArtworksFragmentToArtworkDetailFragment(
                artwordId,
                ArtworkFlow.FAVORITES.name
            ),
            this
        )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun FavoriteArtworksComponent(uiStatus: UIStatus<List<UIArtwork>>) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentColor = VeryLightCyan
        ) {
            when (uiStatus) {
                is UIStatus.Loading -> ArticGalleryLoader()
                is UIStatus.Successful -> {
                    val artworks: List<UIArtwork> = uiStatus.value
                    LazyColumn(
                        modifier = Modifier.background(VeryLightCyan),
                    ) {
                        items(artworks.size) { index ->
                            FavoriteArtworkListItem(artworks[index])
                            HorizontalDivider(color = DeepTeal, thickness = 1.dp)
                        }
                    }
                }

                is UIStatus.Error -> {
                    Timber.e("Error: ${uiStatus.msg}")
                    Text(text = uiStatus.msg)
                }
            }
        }
    }

    @Composable
    fun FavoriteArtworkListItem(uiArtwork: UIArtwork) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 32.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
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
                style = headLine1.copy(
                    fontWeight = FontWeight.Bold,
                    color = DeepTeal
                )
            )
            Column {
                DisplayImageWithCustomLoadingIndicator(
                    modifier = Modifier
                        .size(150.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
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

    @Preview(showBackground = true)
    @Composable
    fun FavoriteArtworksComponentPreview() {
        ArticGalleryTheme {
            FavoriteArtworksComponent(UIStatus.Successful(value = DataProviderMock.getMockedUIArtworks))
        }
    }
}