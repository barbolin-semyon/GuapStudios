package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.modelForJSON.LoginReciveModel
import com.example.guapstudios.data.modelForJSON.RegisterReciveModel
import com.example.guapstudios.data.modelForJSON.TokenModel
import com.example.guapstudios.data.emptities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationRetrofitService {
    @POST("login")
    fun login(@Body loginReciveModel: LoginReciveModel): Call<TokenModel>

    @POST("register")
    fun register(@Body registerReciveModel: RegisterReciveModel): Call<TokenModel>

    @POST("token")
    fun getUserByToken(@Body tokenModel: TokenModel): Call<User>
}