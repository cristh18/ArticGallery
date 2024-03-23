package com.tolodev.artic_gallery.domain.models

object DataProviderMock {

    val getMockedArtworks = listOf(
        Artwork(
            id = 1L,
            title = "Artwork 1",
            description = "This is Artwork 1",
            imageId = "2d484387-2509-5e8e-2c43-22f9981972eb",
            thumbnailAltText = "Image X",
            dimensions = "20x30",
            originPlace = "Place 1",
            dateStart = 2000,
            dateEnd = 2010,
            dateDisplay = "2000 - 2010",
            artistName = "Artist 1",
            artistDisplay = "Van Gogh",
            categories = listOf("Category 1", "Category 2"),
            styleTitle = "Style 1",
            techniques = listOf("Technique 1", "Technique 2")
        ),
        Artwork(
            id = 2L,
            title = "Artwork 2",
            description = "This is Artwork 2",
            imageId = "00dbea0a-d94b-ec69-dc3f-cb4f3eef7a96",
            thumbnailAltText = "Image Z",
            dimensions = "30x40",
            originPlace = "Place 2",
            dateStart = 2010,
            dateEnd = 2020,
            dateDisplay = "2010 - 2020",
            artistName = "Artist 2",
            artistDisplay = "Van Gogh",
            categories = listOf("Category 3", "Category 4"),
            styleTitle = "Style 2",
            techniques = listOf("Technique 3", "Technique 4")
        )
    )
}