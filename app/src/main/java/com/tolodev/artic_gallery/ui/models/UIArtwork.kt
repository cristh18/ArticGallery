package com.tolodev.artic_gallery.ui.models

import com.tolodev.artic_gallery.domain.models.ArtworkThumbnail
import com.tolodev.artic_gallery.domain.models.ImageSize

data class UIArtwork(
    val id: Long,
    val title: String,
    val description: String,
    val imageId: String,
    val thumbnailAltText: String,
    val dimensions: String,
    val originPlace: String,
    val dateStart: Int,
    val dateEnd: Int,
    val dateDisplay: String,
    val artistName: String,
    val artistDisplay: String,
    val categories: List<String>,
    val styleTitle: String,
    val techniques: List<String>,
    val images: Map<ImageSize, ArtworkThumbnail>,
    val isFavorite: Boolean = false,
    val action: () -> Unit? = {}
)
