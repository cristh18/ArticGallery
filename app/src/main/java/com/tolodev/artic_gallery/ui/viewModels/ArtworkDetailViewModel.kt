package com.tolodev.artic_gallery.ui.viewModels

import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolodev.artic_gallery.managers.ArticGalleryManager
import com.tolodev.artic_gallery.ui.mappers.toUIArtwork
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val KEY_ARTWORK_ID = "key_artwork_id"

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(private val articGalleryManager: ArticGalleryManager) :
    ViewModel() {

    private val uiStatus = MutableLiveData<UIStatus<UIArtwork>>()

    private var artworkId: Long = 0
    fun initViewModel(artworkId: Long) {
        if (artworkId > 0) {
            this.artworkId = artworkId
            articGalleryManager.getCurrentArtwork(artworkId)?.let {
                val uiArtwork = it.toUIArtwork()
                uiStatus.value = UIStatus.Successful(uiArtwork)
            }
        }
    }

    fun setInstanceState(): MutableMap<String, Any?> {
        val saveStateData = mutableMapOf<String, Any?>()
        saveStateData[KEY_ARTWORK_ID] = artworkId
        return saveStateData
    }

    fun restoreInstanceState(instanceState: Map<String, Any?>) {
        artworkId = instanceState[KEY_ARTWORK_ID] as Long
        initViewModel(artworkId)
    }

    @CheckResult
    fun uiStatusObserver(): LiveData<UIStatus<UIArtwork>> = uiStatus
}