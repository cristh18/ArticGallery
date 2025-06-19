package com.tolodev.artic_gallery

import com.tolodev.artic_gallery.domain.models.ImageSize
import com.tolodev.artic_gallery.domain.models.ImageUrlGenerator
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageUrlGeneratorTest {
    @Test
    fun generateUrl_returnsMediumImageUrl() {
        val url = ImageUrlGenerator.generateUrl("imageId", ImageSize.MEDIUM)
        assertEquals("https://www.artic.edu/iiif/2/imageId/full/600,/0/default.jpg", url)
    }
}
