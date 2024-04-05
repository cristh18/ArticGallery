package com.tolodev.artic_gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateListOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tolodev.artic_gallery.extensions.composeView
import com.tolodev.artic_gallery.ui.components.screens.artworkDetail.ArtworkDetailScreen
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
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
                val uiStatus: UIStatus<UIArtwork> = modelDataView.last()
                if (uiStatus is UIStatus.Successful) {
                    val uiArtwork = uiStatus.value
                    ArtworkDetailScreen(uiArtwork = uiArtwork,
                        primaryAction = { showHome() },
                        secondaryAction = { viewModel.saveFavoriteArtwork(uiArtwork.id) })
                }
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
}