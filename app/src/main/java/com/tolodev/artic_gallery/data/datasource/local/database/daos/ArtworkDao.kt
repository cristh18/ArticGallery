package com.tolodev.artic_gallery.data.datasource.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolodev.artic_gallery.data.datasource.local.database.entities.ArtworkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM artwork")
    fun getAll(): Flow<List<ArtworkEntity>>

    @Query("SELECT * FROM artwork where id  = :artworkId")
    fun getById(artworkId: Long): Flow<ArtworkEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArtwork(artworkEntity: ArtworkEntity)

    @Query("DELETE FROM artwork WHERE id = :id")
    suspend fun deleteArtwork(id: Int)
}