package com.tolodev.artic_gallery.domain.models

data class Artwork(
    val id: Long,
    val title: String,
    val description: String,
    val imageId: String,
    val dimensions: String,
    val originPlace: String,
    val dateStart: Int,
    val dateEnd: Int,
    val dateDisplay: String,
    val artistName: String, // artist_title
    val categories: List<String>, // category_titles
    val styleTitle: String, // style_title
    val techniques: List<String>, // technique_titles
) {
    fun getImages(): List<ArtworkThumbnail> {
        return if (imageId.isNotBlank()) {
            ImageSize.entries.map { size ->
                ArtworkThumbnail(
                    size = size,
                    imageUrl = ImageUrlGenerator.generateUrl(imageId, size)
                )
            }
        } else {
            emptyList()
        }
    }
}





