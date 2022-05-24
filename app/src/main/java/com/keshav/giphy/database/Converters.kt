package com.keshav.giphy.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.keshav.giphy.model.response.TrendingResponse
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromAnalytics(analytics: TrendingResponse.Data.Analytics?): String? {
        if (analytics == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<TrendingResponse.Data.Analytics?>() {}.type
        return gson.toJson(analytics, type)
    }

    @TypeConverter
    fun toAnalytics(string: String?): TrendingResponse.Data.Analytics? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<TrendingResponse.Data.Analytics?>() {}.type
        return gson.fromJson<TrendingResponse.Data.Analytics>(
            string,
            type
        )
    }

    @TypeConverter
    fun fromImages(analytics: TrendingResponse.Data.Images?): String? {
        if (analytics == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<TrendingResponse.Data.Images?>() {}.type
        return gson.toJson(analytics, type)
    }

    @TypeConverter
    fun toImages(string: String?): TrendingResponse.Data.Images? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<TrendingResponse.Data.Images?>() {}.type
        return gson.fromJson<TrendingResponse.Data.Images>(
            string,
            type
        )
    }

    @TypeConverter
    fun fromUser(analytics: TrendingResponse.Data.User?): String? {
        if (analytics == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<TrendingResponse.Data.User?>() {}.type
        return gson.toJson(analytics, type)
    }

    @TypeConverter
    fun toUser(string: String?): TrendingResponse.Data.User? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<TrendingResponse.Data.User?>() {}.type
        return gson.fromJson<TrendingResponse.Data.User>(
            string,
            type
        )
    }
}