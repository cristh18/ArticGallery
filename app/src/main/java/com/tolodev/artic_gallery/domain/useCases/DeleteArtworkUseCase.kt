package com.tolodev.artic_gallery.domain.useCases

import com.tolodev.artic_gallery.data.repositories.ArtworksRepository
import javax.inject.Inject

class DeleteArtworkUseCase @Inject constructor(private val artworksRepository: ArtworksRepository) {

    suspend operator fun invoke(artworkId: Long) {
        artworksRepository.deleteArtwork(artworkId)
    }
}