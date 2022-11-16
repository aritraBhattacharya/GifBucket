package com.aritra.gifbucket.data.local.db

import androidx.room.*
import com.aritra.gifbucket.data.local.entities.GifResponseEntity

@Dao
interface GifDao {

    @Query("SELECT * FROM gif_response")
    suspend fun getAllGifs(): List<GifResponseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGif(gifResponseEntity: GifResponseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGifs(gifResponseEntities: List<GifResponseEntity>)

    @Update
    suspend fun updateGif(gifResponseEntity: GifResponseEntity)

    @Query("DELETE FROM gif_response")
    suspend fun deleteAllGifs()

    @Query("DELETE FROM gif_response where gif_id= :idOfItemToDelete")
    suspend fun deleteAllGifs(idOfItemToDelete: String)


    // Raw query and more dynamic queries
    // expose flow and livedata


}