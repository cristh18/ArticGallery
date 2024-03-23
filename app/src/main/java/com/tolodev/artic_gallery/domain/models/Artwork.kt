package com.tolodev.artic_gallery.domain.models

data class Artwork(
    val id: Long,
    val title: String,
    val description: String,
    val imageId: String,
    val thumbnailAltText: String, // alt_text
    val dimensions: String,
    val originPlace: String,
    val dateStart: Int,
    val dateEnd: Int,
    val dateDisplay: String,
    val artistName: String, // artist_title
    val artistDisplay: String, // artist_display
    val categories: List<String>, // category_titles
    val styleTitle: String, // style_title
    val techniques: List<String>, // technique_titles
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





