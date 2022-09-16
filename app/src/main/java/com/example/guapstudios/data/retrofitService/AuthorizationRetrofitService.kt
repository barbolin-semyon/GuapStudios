package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.emptities.LoginReciveModel
import com.example.guapstudios.data.emptities.TokenModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthorizationRetrofitService {
    @POST("login")
    fun login(@Body loginReciveModel: LoginReciveModel): Call<TokenModel>

}