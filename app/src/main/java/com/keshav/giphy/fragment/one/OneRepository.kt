package com.keshav.giphy.fragment.one

import com.keshav.giphy.network.ApiHelper

class OneRepository(private val apiHelper: ApiHelper) {

    suspend fun getTrending(limit: Int) = apiHelper.getTrending(limit = limit)
    suspend fun getSearch(limit: Int,searchText: String) = apiHelper.getSearch(limit = limit, searchText =searchText )
}