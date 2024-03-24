package com.tolodev.artic_gallery.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tolodev.artic_gallery.data.datasource.local.database.daos.ArtworkDao
import com.tolodev.artic_gallery.data.datasource.local.database.entities.ArtworkEntity

@Database(entities = [ArtworkEntity::class], version = 1)
abstract class ArticGalleryDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
}