package com.example.guapstudios.data.retrofitService

import retrofit2.http.GET
import retrofit2.http.Query

interface StudioRetrofitService {
    @GET("/studious/get")
    fun getStudio(@Query("id") id: Int)
}