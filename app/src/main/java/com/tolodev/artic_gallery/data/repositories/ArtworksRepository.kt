package com.tolodev.artic_gallery.data.repositories

import com.tolodev.artic_gallery.data.datasource.remote.RemoteDatasource
import com.tolodev.artic_gallery.domain.models.Artwork
import javax.inject.Inject

class ArtworksRepository @Inject constructor(private val remoteDatasource: RemoteDatasource) {

    suspend fun getArtworks(): List<Artwork> {
        return remoteDatasource.getArtworks().data.map { artworkResponse ->
            Artwork(
                id = artworkResponse.id,
                title = artworkResponse.title,
                description = artworkResponse.description.orEmpty(),
                imageId = artworkResponse.imageId.orEmpty(),
                dimensions = artworkResponse.dimensions.orEmpty(),
                originPlace = artworkResponse.placeOfOrigin.orEmpty(),
                dateStart = artworkResponse.dateStart,
                dateEnd = artworkResponse.dateEnd,
                dateDisplay = artworkResponse.dateDisplay,
                artistName = artworkResponse.artistTitle.orEmpty(),
                categories = artworkResponse.categoryTitles.map { it },
                styleTitle = artworkResponse.styleTitle.orEmpty(),
                techniques = artworkResponse.techniqueTitles.map { it }
            )
        }
    }
}