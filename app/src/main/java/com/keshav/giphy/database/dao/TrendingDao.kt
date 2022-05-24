package com.keshav.giphy.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.keshav.giphy.model.response.TrendingResponse

@Dao
interface TrendingDao {

    @Query("SELECT * FROM Data")
    suspend fun getAll(): List<TrendingResponse.Data>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: TrendingResponse.Data)

    @Delete
    suspend fun delete(user: TrendingResponse.Data)
}