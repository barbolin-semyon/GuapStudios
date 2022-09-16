package com.example.guapstudios.data

import com.example.guapstudios.data.retrofitService.AuthorizationRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitService: Retrofit? = null

    fun getAuthorizationRetrofitService(): Retrofit {
        if (retrofitService == null) {
            retrofitService = Retrofit.Builder()
                .baseUrl("http://192.168.0.178:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitService!!
    }
}