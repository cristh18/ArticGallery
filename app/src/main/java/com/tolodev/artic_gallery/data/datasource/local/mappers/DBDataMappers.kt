package com.tolodev.artic_gallery.data.datasource.local.mappers

import com.tolodev.artic_gallery.data.datasource.local.database.entities.ArtworkEntity
import com.tolodev.artic_gallery.domain.models.Artwork

fun ArtworkEntity.toDomainArtwork(): Artwork {
    return Artwork(
        id = id,
        title = title,
        description = description,
        imageId = imageId,
        thumbnailAltText = thumbnailAltText,
        dimensions = dimensions,
        originPlace = originPlace,
        dateStart = dateStart,
        dateEnd = dateEnd,
        dateDisplay = dateDisplay,
        artistName = artistName,
        artistDisplay = artistDisplay,
        categories = categories.split(","),
        styleTitle = styleTitle,
        techniques = techniques.split(","),
        isFavorite = true
    )
}

fun Artwork.toArtworkEntity(): ArtworkEntity {
    return ArtworkEntity(
        id = id,
        title = title,
        description = description,
        imageId = imageId,
        thumbnailAltText = thumbnailAltText,
        dimensions = dimensions,
        originPlace = originPlace,
        dateStart = dateStart,
        dateEnd = dateEnd,
        dateDisplay = dateDisplay,
        artistName = artistName,
        artistDisplay = artistDisplay,
        categories = categories.joinToString(separator = ","),
        styleTitle = styleTitle,
        techniques = techniques.joinToString(separator = ","),
    )
}