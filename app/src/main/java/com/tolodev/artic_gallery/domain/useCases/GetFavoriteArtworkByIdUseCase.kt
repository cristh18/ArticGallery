package com.tolodev.artic_gallery.domain.useCases

import com.tolodev.artic_gallery.data.repositories.ArtworksRepository
import com.tolodev.artic_gallery.domain.models.Artwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteArtworkByIdUseCase @Inject constructor(private val artworksRepository: ArtworksRepository) {

    operator fun invoke(artworkId: Long): Flow<Artwork> {
        return artworksRepository.getFavoriteArtworkById(artworkId)
    }
}