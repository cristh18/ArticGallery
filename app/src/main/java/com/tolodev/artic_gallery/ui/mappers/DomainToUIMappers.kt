package com.tolodev.artic_gallery.ui.mappers

import com.tolodev.artic_gallery.domain.models.Artwork
import com.tolodev.artic_gallery.ui.models.UIArtwork

fun Artwork.toUIArtwork(): UIArtwork {
    return UIArtwork(
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
        categories = categories,
        styleTitle = styleTitle,
        techniques = techniques,
        images = getImages(),
        isFavorite = isFavorite
    )
}