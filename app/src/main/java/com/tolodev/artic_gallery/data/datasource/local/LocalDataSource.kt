package com.tolodev.artic_gallery.data.datasource.local

import com.tolodev.artic_gallery.data.datasource.local.database.ArticGalleryDatabase
import com.tolodev.artic_gallery.data.datasource.local.mappers.toArtworkEntity
import com.tolodev.artic_gallery.data.datasource.local.mappers.toDomainArtwork
import com.tolodev.artic_gallery.domain.models.Artwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(database: ArticGalleryDatabase) :
    ILocalDataSource {

    private val artworkDao = database.artworkDao()
    override fun getArtworks(): Flow<List<Artwork>> {
        return artworkDao.getAll().map { artworks ->
            artworks.map {
                it.toDomainArtwork()
            }
        }
    }

    override fun getArtworkById(artworkId: Long): Flow<Artwork?> {
        return artworkDao.getById(artworkId).map { it?.toDomainArtwork() }
    }

    override suspend fun saveArtwork(artwork: Artwork) {
        artworkDao.insertArtwork(artwork.toArtworkEntity())
    }

    override suspend fun deleteArtwork(id: Int) {
        artworkDao.deleteArtwork(id)
    }
}