package com.keshav.giphy.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getTrending(limit: Int) = apiService.getTrending(limit = limit)
    suspend fun getSearch(limit: Int,searchText: String) = apiService.getSearch(limit = limit, searchText =searchText )
}