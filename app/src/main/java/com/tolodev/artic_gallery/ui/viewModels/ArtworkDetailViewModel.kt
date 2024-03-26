package com.tolodev.artic_gallery.ui.viewModels

import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.domain.useCases.GetFavoriteArtworkByIdUseCase
import com.tolodev.artic_gallery.domain.useCases.SaveArtworkUseCase
import com.tolodev.artic_gallery.managers.ArticGalleryManager
import com.tolodev.artic_gallery.ui.ArtworkFlow
import com.tolodev.artic_gallery.ui.mappers.toUIArtwork
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val KEY_ARTWORK_ID = "key_artwork_id"
private const val KEY_FLOW = "key_flow"

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    private val articGalleryManager: ArticGalleryManager,
    private val saveArtworkUseCase: SaveArtworkUseCase,
    private val getFavoriteArtworkByIdUseCase: GetFavoriteArtworkByIdUseCase
) :
    ViewModel() {

    private val uiStatus = MutableLiveData<UIStatus<UIArtwork>>()

    private var artworkId: Long = 0

    private var flow: String = ""
    fun initViewModel(artworkId: Long, flow: String) {
        this.flow = flow
        if (artworkId > 0) {
            getFavoriteArtworkById(artworkId, flow)
        }
    }

    private fun getFavoriteArtworkById(artworkId: Long, flow: String) {
        viewModelScope.launch {
            getFavoriteArtworkByIdUseCase.invoke(artworkId)
                .flowOn(Dispatchers.IO)
                .onStart { uiStatus.value = UIStatus.Loading(true) }
                .catch { showError(it) }
                .collectLatest {
                    showFavoriteArtwork(it, artworkId, flow)
                }
        }
    }

    private fun updateView(artworkId: Long) {
        this.artworkId = artworkId
        articGalleryManager.getCurrentArtwork(artworkId)?.let {
            val uiArtwork = it.toUIArtwork()
            uiStatus.value = UIStatus.Successful(uiArtwork)
        }
    }

    fun saveFavoriteArtwork(artworkId: Long) {
        viewModelScope.launch {
            articGalleryManager.toggleArtworkFavoriteStatus(artworkId)?.let {
                updateView(it.id)
                if (it.isFavorite) {
                    saveArtworkUseCase.invoke(it)
                } else {
                    // TODO DELETE OR UPDATE CURRENT REGISTER
                }
            }
        }
    }

    private fun showError(throwable: Throwable) {
        val errorMessage = "Has occurred an error"
        Timber.e(throwable, errorMessage)
        uiStatus.value = UIStatus.Error(throwable.message.orEmpty(), throwable)
    }

    private fun showFavoriteArtwork(artwork: Artwork?, artworkId: Long, flow: String) {
        artwork?.let { savedArtwork ->
            if (flow == ArtworkFlow.FAVORITES.name) {
                this.artworkId = savedArtwork.id
                this.flow = flow
                val uiArtwork = savedArtwork.toUIArtwork()
                uiStatus.value = UIStatus.Successful(uiArtwork)
            } else {
                articGalleryManager.updateArtworkFavoriteStatus(
                    savedArtwork.id,
                    savedArtwork.isFavorite
                )?.let {
                    updateView(artworkId)
                }
            }
        } ?: run {
            updateView(artworkId)
        }
    }

    fun isFavoriteFlow(): Boolean {
        return flow == ArtworkFlow.FAVORITES.name
    }

    fun setInstanceState(): MutableMap<String, Any?> {
        val saveStateData = mutableMapOf<String, Any?>()
        saveStateData[KEY_ARTWORK_ID] = artworkId
        saveStateData[KEY_FLOW] = flow
        return saveStateData
    }

    fun restoreInstanceState(instanceState: Map<String, Any?>) {
        artworkId = instanceState[KEY_ARTWORK_ID] as Long
        flow = instanceState[KEY_FLOW] as String
        initViewModel(artworkId, flow)
    }

    @CheckResult
    fun uiStatusObserver(): LiveData<UIStatus<UIArtwork>> = uiStatus
}