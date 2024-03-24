package com.tolodev.artic_gallery.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.domain.useCases.GetArtworksUseCase
import com.tolodev.artic_gallery.managers.ArticGalleryManager
import com.tolodev.artic_gallery.ui.mappers.toUIArtwork
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import com.tolodev.artic_gallery.utils.getHttpErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArtworksUseCase: GetArtworksUseCase,
    private val articGalleryManager: ArticGalleryManager
) :
    ViewModel() {

    private val uiStatus = MutableLiveData<UIStatus<List<UIArtwork>>>()

    fun initViewModel() {
        getArtworks()
    }

    private fun getArtworks() {
        viewModelScope.launch {
            getArtworksFlow().collectLatest {
                uiStatus.value = UIStatus.Successful(it)
            }
        }
    }

    private fun getArtworksFlow(): Flow<List<UIArtwork>> {
        return flow {
            val artworks: List<Artwork> = getArtworksUseCase.invoke()
            articGalleryManager.setArtworks(artworks)
            val uiArtworks: List<UIArtwork> = artworks.map { it.toUIArtwork() }
            emit(uiArtworks)
        }.onStart { uiStatus.postValue(UIStatus.Loading(true)) }
            .flowOn(Dispatchers.IO)
            .catch { showError(it) }
    }

    private fun showError(throwable: Throwable) {
        val errorMessage = getHttpErrorMessage(throwable)
        Timber.e(throwable, errorMessage)
        uiStatus.value = UIStatus.Error(errorMessage, throwable)
    }

    fun uiStatusObserver(): LiveData<UIStatus<List<UIArtwork>>> = uiStatus
}