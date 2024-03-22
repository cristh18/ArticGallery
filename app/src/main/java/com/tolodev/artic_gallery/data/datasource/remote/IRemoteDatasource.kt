package com.tolodev.artic_gallery.data.datasource.remote

import com.tolodev.artic_gallery.data.datasource.remote.models.ArtworkResponse

interface IRemoteDatasource {

    suspend fun getArtworks(): ArtworkResponse
}