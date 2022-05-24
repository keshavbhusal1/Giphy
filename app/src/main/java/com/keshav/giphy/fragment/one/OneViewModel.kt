package com.keshav.giphy.fragment.one

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.keshav.giphy.database.DatabaseHelper
import com.keshav.giphy.model.response.TrendingResponse
import com.keshav.giphy.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OneViewModel(private val oneRepository: OneRepository) : ViewModel() {
    fun getTrending(limit: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = oneRepository.getTrending(limit = limit)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getSearch(limit: Int,searchText: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = oneRepository.getSearch(limit = limit, searchText = searchText)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insertData(model : TrendingResponse.Data,dbHelper: DatabaseHelper){
        viewModelScope.launch {
            try {
                dbHelper.insert(model)
            } catch (e: Exception) {
                Log.d("TAG", "insertData: "+e.localizedMessage)
            }
        }
    }
}