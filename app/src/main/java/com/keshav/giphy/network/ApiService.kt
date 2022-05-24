package com.keshav.giphy.network

import com.keshav.giphy.model.response.TrendingResponse
import com.keshav.giphy.utils.kApiKey
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("gifs/trending")
    suspend fun getTrending(
        @Query("api_key") apiKey: String= kApiKey,
        @Query("limit") limit: Int=25,
        @Query("rating") rating: String= "g",
    ): TrendingResponse


    @GET("gifs/trending")
    suspend fun getSearch(
        @Query("api_key") apiKey: String=kApiKey,
        @Query("q") searchText: String,
        @Query("limit") limit: Int=25,
        @Query("offset") offset: Int=0,
        @Query("rating") rating: String= "g",
        @Query("lang") lang: String= "en",
    ): TrendingResponse

}