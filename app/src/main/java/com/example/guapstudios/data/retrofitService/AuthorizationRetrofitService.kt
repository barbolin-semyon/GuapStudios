package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.emptities.LoginReciveModel
import com.example.guapstudios.data.emptities.RegisterReciveModel
import com.example.guapstudios.data.emptities.TokenModel
import com.example.guapstudios.data.emptities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthorizationRetrofitService {
    @POST("login")
    fun login(@Body loginReciveModel: LoginReciveModel): Call<TokenModel>

    @POST("register")
    fun register(@Body registerReciveModel: RegisterReciveModel): Call<TokenModel>

    @POST("token")
    fun getUserByToken(@Body tokenModel: TokenModel): Call<User>
}