package com.tolodev.artic_gallery.data.datasource.remote.mappers

import com.tolodev.artic_gallery.data.datasource.remote.models.ArtworkResponse
import com.tolodev.artic_gallery.domain.models.Artwork

fun ArtworkResponse.toDomainArtworks(): List<Artwork> {
    return data.map { artworkResponse ->
        Artwork(
            id = artworkResponse.id,
            title = artworkResponse.title,
            description = artworkResponse.description.orEmpty(),
            imageId = artworkResponse.imageId.orEmpty(),
            thumbnailAltText = artworkResponse.thumbnail?.altText.orEmpty(),
            dimensions = artworkResponse.dimensions.orEmpty(),
            originPlace = artworkResponse.placeOfOrigin.orEmpty(),
            dateStart = artworkResponse.dateStart,
            dateEnd = artworkResponse.dateEnd,
            dateDisplay = artworkResponse.dateDisplay,
            artistName = artworkResponse.artistTitle.orEmpty(),
            artistDisplay = artworkResponse.artistDisplay.orEmpty(),
            categories = artworkResponse.categoryTitles.map { it },
            styleTitle = artworkResponse.styleTitle.orEmpty(),
            techniques = artworkResponse.techniqueTitles.map { it }
        )
    }
}