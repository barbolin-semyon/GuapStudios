package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.emptities.Studio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudioRetrofitService {
    @GET("/studious/get")
    fun getStudio(@Query("id") id: Int): Call<Studio>
}