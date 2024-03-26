package com.tolodev.artic_gallery.data.repositories

import com.tolodev.artic_gallery.data.datasource.local.LocalDataSource
import com.tolodev.artic_gallery.data.datasource.remote.RemoteDatasource
import com.tolodev.artic_gallery.data.datasource.remote.mappers.toDomainArtworks
import com.tolodev.artic_gallery.domain.models.Artwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class ArtworksRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDatasource: RemoteDatasource
) {

    fun getFavoriteArtworks(): Flow<List<Artwork>> {
        return localDataSource.getArtworks()
    }

    fun getFavoriteArtworkById(artwordId: Long): Flow<Artwork?> {
        return localDataSource.getArtworkById(artwordId)
    }

    suspend fun saveArtwork(artwork: Artwork) {
        localDataSource.saveArtwork(artwork)
    }

    suspend fun deleteArtwork(artworkId: Long) {
        localDataSource.deleteArtwork(artworkId)
    }

    suspend fun getArtworks(): List<Artwork> {
        return withTimeout(30_000) { remoteDatasource.getArtworks().toDomainArtworks() }
    }
}