package com.tolodev.artic_gallery.data.datasource.remote

import com.tolodev.artic_gallery.data.datasource.remote.api.ArticGalleryApi
import com.tolodev.artic_gallery.data.datasource.remote.models.ArtworkResponse
import javax.inject.Inject

class RemoteDatasource @Inject constructor(private val articGalleryApi: ArticGalleryApi) :
    IRemoteDatasource {
    override suspend fun getArtworks(): ArtworkResponse {
        return articGalleryApi.getArtworks()
    }
}