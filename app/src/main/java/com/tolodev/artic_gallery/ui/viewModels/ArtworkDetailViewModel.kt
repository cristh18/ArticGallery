package com.tolodev.artic_gallery.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolodev.artic_gallery.managers.ArticGalleryManager
import com.tolodev.artic_gallery.ui.mappers.toUIArtwork
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(private val articGalleryManager: ArticGalleryManager) :
    ViewModel() {

    private val uiStatus = MutableLiveData<UIStatus<UIArtwork>>()
    fun initViewModel(artworkId: Long) {
        articGalleryManager.getCurrentArtwork(artworkId)?.let {
            val uiArtwork = it.toUIArtwork()
            uiStatus.value = UIStatus.Successful(uiArtwork)
        }
    }

    override fun onCleared() {
        super.onCleared()
        articGalleryManager.clearWorkflowManager()
    }

    fun uiStatusObserver(): LiveData<UIStatus<UIArtwork>> = uiStatus
}