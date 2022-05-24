package com.keshav.giphy.database

import androidx.lifecycle.LiveData
import com.keshav.giphy.model.response.TrendingResponse

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getAll(): List<TrendingResponse.Data>  = appDatabase.trendingDao().getAll()

    override suspend fun insert(data: TrendingResponse.Data)  = appDatabase.trendingDao().insert(data)

    override suspend fun delete(data: TrendingResponse.Data)  = appDatabase.trendingDao().delete(data)


}