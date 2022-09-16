package com.example.guapstudios.data

import com.example.guapstudios.data.retrofitService.AuthorizationRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitService: Retrofit? = null

    fun getAuthorizationRetrofitService(): Retrofit {
        if (retrofitService == null) {
            retrofitService = Retrofit.Builder()
                .baseUrl("http://0.0.0.0:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitService!!
    }
}