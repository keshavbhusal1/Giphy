package com.keshav.giphy.fragment.two

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keshav.giphy.database.DatabaseHelper
import com.keshav.giphy.model.response.TrendingResponse
import kotlinx.coroutines.launch

class TwoViewModel : ViewModel() {
    fun getAllData(dbHelper: DatabaseHelper):LiveData<List<TrendingResponse.Data>>{
      val list :MutableLiveData<List<TrendingResponse.Data>> = MutableLiveData()
        viewModelScope.launch {
            try {
                list.value=dbHelper.getAll()
            } catch (e: Exception) {
                Log.d("TAG", "getAllData: "+e.localizedMessage)
                list.value = emptyList()

            }
        }
        return list



    }

    fun deleteData(model : TrendingResponse.Data,dbHelper: DatabaseHelper):LiveData<Boolean>{
        val isDelete :MutableLiveData<Boolean> = MutableLiveData()

        viewModelScope.launch {
            try {
                dbHelper.delete(model)
                isDelete.value =true
            } catch (e: Exception) {
                isDelete.value = false

            }
        }
        return isDelete
    }
}