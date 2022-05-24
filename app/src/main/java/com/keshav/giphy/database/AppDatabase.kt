package com.keshav.giphy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.keshav.giphy.database.dao.TrendingDao
import com.keshav.giphy.model.response.TrendingResponse

@Database(entities = [TrendingResponse.Data::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trendingDao(): TrendingDao

}