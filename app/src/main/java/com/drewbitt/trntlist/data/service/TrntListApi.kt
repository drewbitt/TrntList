package com.drewbitt.trntlist.data.service

import com.drewbitt.trntlist.data.model.TrntJson
import retrofit2.Call
import retrofit2.http.GET

interface TrntListApi {

    @GET("files")
    fun getList(): Call<List<TrntJson>>
}