package com.tolodev.artic_gallery.data.datasource.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artwork")
data class ArtworkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val imageId: String,
    val thumbnailAltText: String,
    val dimensions: String,
    val originPlace: String,
    val dateStart: Int,
    val dateEnd: Int,
    val dateDisplay: String,
    val artistName: String,
    val artistDisplay: String,
    val categories: String,
    val styleTitle: String,
    val techniques: String
)