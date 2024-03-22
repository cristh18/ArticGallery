package com.tolodev.artic_gallery.data.datasource.remote.api

import retrofit2.http.GET

interface ArticGalleryApi {

    @GET("/artworks")
    suspend fun getArtworks(): Any

}