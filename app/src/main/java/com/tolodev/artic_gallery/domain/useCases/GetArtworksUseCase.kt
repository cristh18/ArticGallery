package com.tolodev.artic_gallery.domain.useCases

import com.tolodev.artic_gallery.data.repositories.ArtworksRepository
import javax.inject.Inject

class GetArtworksUseCase @Inject constructor(private val artworksRepository: ArtworksRepository) {

    suspend operator fun invoke(): Any {
        return artworksRepository.getArtworks()
    }
}