package com.tolodev.artic_gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.extensions.composeView
import com.tolodev.artic_gallery.ui.ArtworkFlow
import com.tolodev.artic_gallery.ui.activities.MainActivity
import com.tolodev.artic_gallery.ui.components.general.ArticGalleryLoader
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme
import com.tolodev.artic_gallery.ui.viewModels.HomeViewModel
import com.tolodev.artic_gallery.utils.startDestination
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var modelDataView = mutableStateListOf<UIStatus<List<UIArtwork>>>()

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).showBottomNavigationView()
        return composeView {
            modelDataView.forEach { model ->
                HomeComponent(model)
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
            HomeFragmentDirections.actionHomeFragmentToArtworkDetailFragment(
                artwordId,
                ArtworkFlow.RECENT.name
            ),
            this
        )
    }

    @Composable
    fun HomeComponent(uiStatus: UIStatus<List<UIArtwork>>) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentColor = MaterialTheme.colorScheme.background,
            content = {
                when (uiStatus) {
                    is UIStatus.Loading -> ArticGalleryLoader()
                    is UIStatus.Successful -> ArticGalleryHomeContent(
                        paddingValues = it,
                        artworks = uiStatus.value
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
    fun ArticGalleryHomeContent(paddingValues: PaddingValues, artworks: List<UIArtwork>) {
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            columns = GridCells.Fixed(2),
            contentPadding = paddingValues
        ) {
            items(artworks) { artwork ->
                ArtworkListItem(artwork)
            }
        }
    }

    @Composable
    fun ArtworkListItem(uiArtwork: UIArtwork) {
        Timber.d(
            "ArtworkListItem",
            "Title: ${uiArtwork.title}, Description: ${uiArtwork.description}"
        )
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    Timber.e("Selected: " + uiArtwork.title)
                    showArtworkDetail(uiArtwork.id)
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DisplayImageWithCustomLoadingIndicator(
                    modifier = Modifier
                        .size(200.dp)
                        .fillMaxWidth(),
                    url = uiArtwork.images[ImageSize.TINY]?.imageUrl.orEmpty(),
                    contentDescription = uiArtwork.thumbnailAltText
                )
                Text(
                    text = uiArtwork.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun HomeComponentPreview() {
        ArticGalleryTheme {
            ArticGalleryLoader()
            HomeComponent(UIStatus.Successful(value = DataProviderMock.getMockedUIArtworks))
        }
    }
}





