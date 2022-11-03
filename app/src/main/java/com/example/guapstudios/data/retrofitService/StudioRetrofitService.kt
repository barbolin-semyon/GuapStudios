package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.emptities.Studio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudioRetrofitService {
    @GET("/studios/get")
    fun getStudio(@Query("name") name: String): Call<Studio>
}