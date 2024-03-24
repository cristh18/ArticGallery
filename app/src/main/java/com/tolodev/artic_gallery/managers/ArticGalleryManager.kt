package com.tolodev.artic_gallery.managers

import com.tolodev.artic_gallery.domain.models.Artwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ArticGalleryManager private constructor() : CoroutineScope {

    private val TAG = this@ArticGalleryManager::class.java.name

    private var artworks: List<Artwork>? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var job: Job = SupervisorJob()

    companion object {
        private var articGalleryManagerInstance: ArticGalleryManager? = null
        val instance: ArticGalleryManager
            get() {
                if (articGalleryManagerInstance == null) {
                    throw NullPointerException("Before call this method you have to init it")
                }
                return articGalleryManagerInstance as ArticGalleryManager
            }

        fun init() {
            if (articGalleryManagerInstance == null) {
                articGalleryManagerInstance =
                    ArticGalleryManager()
            }
        }
    }

    fun setArtworks(artworks: List<Artwork>?) {
        this.artworks = artworks
    }

    fun getCurrentArtwork(artworkId: Long): Artwork? {
        return artworks.orEmpty().firstOrNull { it.id == artworkId }
    }

    private fun setDefaultValues() {
        artworks = null
    }

    fun clearWorkflowManager() {
        launch(Dispatchers.Main) {
            setDefaultValues()
        }
    }
}