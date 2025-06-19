package com.tolodev.artic_gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateListOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tolodev.artic_gallery.extensions.composeView
import com.tolodev.artic_gallery.ui.ArtworkFlow
import com.tolodev.artic_gallery.ui.activities.MainActivity
import com.tolodev.artic_gallery.ui.components.screens.favoriteArtworks.FavoriteArtworksScreen
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.viewModels.FavoriteArtworksViewModel
import com.tolodev.artic_gallery.utils.startDestination
import dagger.hilt.android.AndroidEntryPoint

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
                FavoriteArtworksScreen(model, primaryAction = {
                    viewModel.initViewModel()
                }, secondaryAction = {
                    showArtworkDetail(it)
                })
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

    private fun showArtworkDetail(artworkId: Long) {
        (requireActivity() as MainActivity).hideBottomNavigationView()
        startDestination(
            FavoriteArtworksFragmentDirections.actionFavoriteArtworksFragmentToArtworkDetailFragment(
                artworkId,
                ArtworkFlow.FAVORITES.name
            ),
            this
        )
    }
}