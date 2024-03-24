package com.tolodev.artic_gallery.data.repositories

import com.tolodev.artic_gallery.data.datasource.remote.RemoteDatasource
import com.tolodev.artic_gallery.data.datasource.remote.mappers.toDomainArtworks
import com.tolodev.artic_gallery.domain.models.Artwork
import javax.inject.Inject

class ArtworksRepository @Inject constructor(private val remoteDatasource: RemoteDatasource) {

    suspend fun getArtworks(): List<Artwork> {
        return remoteDatasource.getArtworks().toDomainArtworks()
    }
}