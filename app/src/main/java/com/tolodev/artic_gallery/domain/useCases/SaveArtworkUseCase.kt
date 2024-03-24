package com.tolodev.artic_gallery.domain.useCases

import com.tolodev.artic_gallery.data.repositories.ArtworksRepository
import com.tolodev.artic_gallery.domain.models.Artwork
import javax.inject.Inject

class SaveArtworkUseCase @Inject constructor(private val artworksRepository: ArtworksRepository) {

    suspend operator fun invoke(artwork: Artwork) = artworksRepository.saveArtwork(artwork)
}