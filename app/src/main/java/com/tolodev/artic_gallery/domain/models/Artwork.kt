package com.tolodev.artic_gallery.domain.models

data class Artwork(
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
) {

    fun getImages(): Map<ImageSize, ArtworkThumbnail> {
        val images = HashMap<ImageSize, ArtworkThumbnail>()
        return if (imageId.isNotBlank()) {
            ImageSize.entries.forEach { size ->
                val artworkThumbnail = ArtworkThumbnail(
                    size = size,
                    imageUrl = ImageUrlGenerator.generateUrl(imageId, size)
                )
                images[size] = artworkThumbnail
            }
            return images
        } else {
            emptyMap()
        }
    }
}





