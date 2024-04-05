package com.tolodev.artic_gallery.ui.models

import android.content.Context
import com.tolodev.artic_gallery.R
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
) {
    fun getArtworkDetails(context: Context): List<String> {
        val artworkDetails = mutableListOf<String>()
        if (dimensions.isNotEmpty()) {
            val dimensionsText: String =
                context.getString(R.string.copy_dimensions) + dimensions
            artworkDetails += dimensionsText
        }

        if (originPlace.isNotEmpty()) {
            val originPlaceText: String =
                context.getString(R.string.copy_origin_place) + originPlace
            artworkDetails += originPlaceText
        }

        if (dateStart > 0 && dateEnd > 0) {
            val completionDateText: String =
                context.getString(R.string.copy_completion_date) + dateStart + " - " + dateEnd
            artworkDetails += completionDateText
        }

        if (dateDisplay.isNotEmpty()) {
            val displayText: String =
                context.getString(R.string.copy_exhibition_date) + dateDisplay
            artworkDetails += displayText
        }

        if (artistName.isNotEmpty()) {
            val artistNameText: String =
                context.getString(R.string.copy_artist) + artistName
            artworkDetails += artistNameText
        }

        if (artistDisplay.isNotEmpty()) {
            val artistDisplayText: String =
                context.getString(R.string.copy_artist_lifetime) + artistDisplay
            artworkDetails += artistDisplayText
        }

        if (categories.isNotEmpty()) {
            val categoriesText: String =
                context.getString(R.string.copy_categories) + categories.joinToString(
                    separator = ", "
                )
            artworkDetails += categoriesText
        }

        if (styleTitle.isNotEmpty()) {
            val styleText: String =
                context.getString(R.string.copy_style) + styleTitle
            artworkDetails += styleText
        }

        if (techniques.isNotEmpty()) {
            val techniquesText: String =
                context.getString(R.string.copy_categories) + techniques.joinToString(
                    separator = ", "
                )
            artworkDetails += techniquesText
        }


        return artworkDetails.toList()
    }
}
