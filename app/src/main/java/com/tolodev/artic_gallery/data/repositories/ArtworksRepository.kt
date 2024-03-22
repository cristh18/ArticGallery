package com.tolodev.artic_gallery.data.repositories

import com.tolodev.artic_gallery.data.datasource.remote.RemoteDatasource
import com.tolodev.artic_gallery.data.datasource.remote.models.ArtworkResponse
import javax.inject.Inject

class ArtworksRepository @Inject constructor(private val remoteDatasource: RemoteDatasource) {

    suspend fun getArtworks(): ArtworkResponse {
        return remoteDatasource.getArtworks()
    }
}