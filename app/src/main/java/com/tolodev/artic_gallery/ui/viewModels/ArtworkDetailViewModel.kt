package com.tolodev.artic_gallery.ui.viewModels

import android.content.Context
import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolodev.artic_gallery.R
import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.domain.useCases.DeleteArtworkUseCase
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
    private val getFavoriteArtworkByIdUseCase: GetFavoriteArtworkByIdUseCase,
    private val deleteArtworkUseCase: DeleteArtworkUseCase
) :
    ViewModel() {

    private val uiStatus = MutableLiveData<UIStatus<UIArtwork>>()

    private val navigate = MutableLiveData<Unit>()

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
            if (isFavoriteFlow()) {
                deleteArtwork(artworkId) { showFavoriteArtworksList() }
            } else {
                articGalleryManager.toggleArtworkFavoriteStatus(artworkId)?.let {
                    updateView(it.id)
                    if (it.isFavorite) {
                        saveArtworkUseCase.invoke(it)
                    } else {
                        deleteArtwork(artworkId)
                    }
                }
            }

        }
    }

    private fun deleteArtwork(artworkId: Long, action: (() -> Unit)? = null) {
        viewModelScope.launch {
            deleteArtworkUseCase.invoke(artworkId)
            action?.invoke()
        }
    }

    private fun showFavoriteArtworksList() {
        navigate.postValue(Unit)
    }

    private fun showError(throwable: Throwable) {
        val errorMessage = "Has occurred an error"
        Timber.e(throwable, errorMessage)
        uiStatus.value = UIStatus.Error(throwable.message.orEmpty(), throwable)
    }

    private fun showFavoriteArtwork(artwork: Artwork?, artworkId: Long, flow: String) {
        artwork?.let { savedArtwork ->
            if (isFavoriteFlow()) {
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

    fun getArtworkDetails(uiArtwork: UIArtwork, context: Context): List<String> {
        val artworkDetails = mutableListOf<String>()
        if (uiArtwork.dimensions.isNotEmpty()) {
            val dimensionsText: String =
                context.getString(R.string.copy_dimensions) + uiArtwork.dimensions
            artworkDetails += dimensionsText
        }

        if (uiArtwork.originPlace.isNotEmpty()) {
            val originPlaceText: String =
                context.getString(R.string.copy_origin_place) + uiArtwork.originPlace
            artworkDetails += originPlaceText
        }

        if (uiArtwork.dateStart > 0 && uiArtwork.dateEnd > 0) {
            val completionDateText: String =
                context.getString(R.string.copy_completion_date) + uiArtwork.dateStart + " - " + uiArtwork.dateEnd
            artworkDetails += completionDateText
        }

        if (uiArtwork.dateDisplay.isNotEmpty()) {
            val displayText: String =
                context.getString(R.string.copy_exhibition_date) + uiArtwork.dateDisplay
            artworkDetails += displayText
        }

        if (uiArtwork.artistName.isNotEmpty()) {
            val artistNameText: String =
                context.getString(R.string.copy_artist) + uiArtwork.artistName
            artworkDetails += artistNameText
        }

        if (uiArtwork.artistDisplay.isNotEmpty()) {
            val artistDisplayText: String =
                context.getString(R.string.copy_artist_lifetime) + uiArtwork.artistDisplay
            artworkDetails += artistDisplayText
        }

        if (uiArtwork.categories.isNotEmpty()) {
            val categoriesText: String =
                context.getString(R.string.copy_categories) + uiArtwork.categories.joinToString(
                    separator = ", "
                )
            artworkDetails += categoriesText
        }

        if (uiArtwork.styleTitle.isNotEmpty()) {
            val styleText: String =
                context.getString(R.string.copy_style) + uiArtwork.styleTitle
            artworkDetails += styleText
        }

        if (uiArtwork.techniques.isNotEmpty()) {
            val techniquesText: String =
                context.getString(R.string.copy_categories) + uiArtwork.techniques.joinToString(
                    separator = ", "
                )
            artworkDetails += techniquesText
        }


        return artworkDetails.toList()
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

    @CheckResult
    fun navigateObserver(): LiveData<Unit> = navigate
}