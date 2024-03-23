package com.tolodev.artic_gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateListOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.extensions.composeView
import com.tolodev.artic_gallery.ui.components.home.HomeComponent
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var modelDataView = mutableStateListOf<UIStatus<List<Artwork>>>()

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    private fun handleUIStatus(uiStatus: UIStatus<Any>) {
        when (uiStatus) {
            is UIStatus.Successful -> Timber.e("POKEMON - Artworks: " + uiStatus.value)
            else -> Timber.d("Unexpected status")
        }
    }

    private fun setupView(uiStatus: UIStatus<List<Artwork>>) {
        modelDataView.clear()
        modelDataView.addAll(listOf(uiStatus))
    }
}





