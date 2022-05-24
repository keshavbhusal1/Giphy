package com.keshav.giphy.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keshav.giphy.fragment.one.OneRepository
import com.keshav.giphy.fragment.one.OneViewModel
import com.keshav.giphy.network.ApiHelper


class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OneViewModel::class.java)) {
            return OneViewModel(OneRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}