package com.tolodev.artic_gallery.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.extensions.composeView
import com.tolodev.artic_gallery.ui.components.general.DisplayImageWithCustomLoadingIndicator
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme
import com.tolodev.artic_gallery.ui.viewModels.ArtworkDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment() {

    private var modelDataView = mutableStateListOf<UIStatus<UIArtwork>>()

    private val viewModel by viewModels<ArtworkDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
            args.artworkId.let {
                viewModel.initViewModel(it)
                subscribe()
                Timber.e("artworkId: $it")
            }
        }
    }

    private fun subscribe() {
        viewModel.uiStatusObserver().observe(viewLifecycleOwner, ::setupView)
    }

    private fun setupView(uiStatus: UIStatus<UIArtwork>) {
        modelDataView.clear()
        modelDataView.add(uiStatus)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun ArtworkComponent(uiStatus: UIStatus<UIArtwork>) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentColor = MaterialTheme.colorScheme.background,
            content = {
                if (uiStatus is UIStatus.Successful) {
                    val uiArtwork: UIArtwork = uiStatus.value
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                text = uiArtwork.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }

                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                val modifier: Modifier = Modifier
                                    .size(400.dp)
                                    .align(Alignment.Center) // Align to center
                                DisplayImageWithCustomLoadingIndicator(
                                    modifier = modifier,
                                    url = uiArtwork.images[ImageSize.BIG]?.imageUrl.orEmpty(),
                                    contentDescription = uiArtwork.thumbnailAltText
                                )
                            }
                        }

                        item {
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
                        }

                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .background(Color.LightGray.copy(alpha = 0.2f))
                                    .padding(10.dp),
                                text = uiArtwork.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Black,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun ArtworkComponentPreview() {
        ArticGalleryTheme {
            ArtworkComponent(UIStatus.Successful(value = DataProviderMock.getMockedUIArtworks[0]))
        }
    }
}