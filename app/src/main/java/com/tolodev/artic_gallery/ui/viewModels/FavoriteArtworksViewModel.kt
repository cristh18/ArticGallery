package com.tolodev.artic_gallery.ui.viewModels

import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.domain.useCases.GetFavoriteArtworks
import com.tolodev.artic_gallery.ui.mappers.toUIArtwork
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteArtworksViewModel @Inject constructor(
    private val getFavoriteArtworks: GetFavoriteArtworks
) :
    ViewModel() {

    fun initViewModel() {
        getFavoriteArtworks()
    }

    private val uiStatus = MutableLiveData<UIStatus<List<UIArtwork>>>()

    private fun getFavoriteArtworks() {
        viewModelScope.launch {
            getFavoriteArtworks.invoke()
                .flowOn(Dispatchers.IO)
                .onStart { uiStatus.value = UIStatus.Loading(true) }
                .catch { showError(it) }
                .collect(::showFavoriteArtworks)
        }
    }

    private fun showError(throwable: Throwable) {
        val errorMessage = "Has occurred an error"
        Timber.e(throwable, errorMessage)
        uiStatus.value = UIStatus.Error(throwable.message.orEmpty(), throwable)
    }

    private fun showFavoriteArtworks(artworks: List<Artwork>) {
        val uiLinks = artworks.map { it.toUIArtwork() }
        uiStatus.value = UIStatus.Successful(uiLinks)
    }

    @CheckResult
    fun uiStatusObserver(): LiveData<UIStatus<List<UIArtwork>>> = uiStatus
}