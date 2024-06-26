package com.tolodev.artic_gallery.data.datasource.local

import com.tolodev.artic_gallery.domain.models.Artwork
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {

    fun getArtworks(): Flow<List<Artwork>>

    fun getArtworkById(artworkId: Long): Flow<Artwork?>

    suspend fun saveArtwork(artwork: Artwork)

    suspend fun deleteArtwork(artworkId: Long)
}