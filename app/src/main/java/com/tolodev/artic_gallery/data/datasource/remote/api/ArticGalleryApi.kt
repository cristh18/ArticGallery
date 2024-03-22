package com.tolodev.artic_gallery.data.datasource.remote.api

import com.tolodev.artic_gallery.data.datasource.remote.models.ArtworkResponse
import retrofit2.http.GET

interface ArticGalleryApi {

    @GET("api/v1/artworks")
    suspend fun getArtworks(): ArtworkResponse

}