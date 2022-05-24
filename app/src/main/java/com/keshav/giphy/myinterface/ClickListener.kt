package com.keshav.giphy.myinterface

import com.keshav.giphy.model.response.TrendingResponse

interface ClickListener {
    fun click(model : TrendingResponse.Data )
}