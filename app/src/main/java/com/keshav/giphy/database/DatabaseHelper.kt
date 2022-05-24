package com.keshav.giphy.database

import com.keshav.giphy.model.response.TrendingResponse


    interface DatabaseHelper {
        suspend fun getAll(): List<TrendingResponse.Data>

        suspend fun insert(data: TrendingResponse.Data)
        suspend fun delete(data: TrendingResponse.Data)

    }
