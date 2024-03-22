package com.tolodev.artic_gallery.data.repositories

import com.tolodev.artic_gallery.data.datasource.remote.RemoteDatasource
import javax.inject.Inject

class ArtworksRepository @Inject constructor(private val remoteDatasource: RemoteDatasource) {

    suspend fun getArtworks(): Any {
        return remoteDatasource.getArtworks()
    }
}