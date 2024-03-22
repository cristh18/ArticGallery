package com.tolodev.artic_gallery.data.datasource.remote

interface IRemoteDatasource {

    suspend fun getArtworks(): Any
}