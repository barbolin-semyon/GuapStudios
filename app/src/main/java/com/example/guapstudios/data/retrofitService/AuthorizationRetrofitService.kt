package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.modelForJSON.LoginReciveModel
import com.example.guapstudios.data.modelForJSON.RegisterReciveModel
import com.example.guapstudios.data.modelForJSON.TokenModel
import com.example.guapstudios.data.emptities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizationRetrofitService {
    @POST("login")
    fun login(@Body loginReciveModel: LoginReciveModel): Call<TokenModel>

    @POST("register")
    fun register(@Body registerReciveModel: RegisterReciveModel): Call<TokenModel>

    @GET("performToken")
    fun getUserByToken(@Query("token") token: String): Call<User>
}