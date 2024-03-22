package com.tolodev.artic_gallery.domain.models

object ImageUrlGenerator {
    private const val BASE_URL = "https://www.artic.edu/iiif/2/"

    fun generateUrl(imageId: String, size: ImageSize): String {
        return "$BASE_URL$imageId/full/${size.size},/0/default.jpg"
    }
}