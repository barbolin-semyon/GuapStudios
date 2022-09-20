package com.example.guapstudios.viewModel

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.emptities.LoginReciveModel
import com.example.guapstudios.data.emptities.RegisterReciveModel
import com.example.guapstudios.data.emptities.TokenModel
import com.example.guapstudios.data.emptities.User
import com.example.guapstudios.data.retrofitService.AuthorizationRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AuthorizationViewModel : ViewModel() {

    private val client = RetrofitClient.getAuthorizationRetrofitService()
        .create(AuthorizationRetrofitService::class.java)

    private val _isAuthorization = MutableLiveData<Boolean>(false)
    val isAuthorization: LiveData<Boolean>
        get() = _isAuthorization

    private val _token = MutableLiveData("")
    val token : LiveData<String>
        get() = _token

    fun login(reciveModel: LoginReciveModel) {
        client.login(reciveModel).enqueue(object : Callback<TokenModel> {
            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _token.value = it.token
                        getUserByToken(it)
                    }
                } else {
                    Log.i("Retrofit", "login viewMOdel error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                Log.i("Retrofit", "login view Model: $t")
            }

        })
    }

    fun register(registerReciveModel: RegisterReciveModel) {
        client.register(registerReciveModel).enqueue(object : Callback<TokenModel> {
            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _token.value = it.token
                        getUserByToken(it)
                    }
                } else {
                    Log.i("Retrofit", "login viewMOdel error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                Log.i("Retrofit", "register viewModel: $t")
            }
        })
    }

    fun getUserByToken(tokenModel: TokenModel) {
        client.getUserByToken(tokenModel).enqueue( object  : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _isAuthorization.value = true
                } else {
                    Log.i("Retrofit", "login viewMOdel error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("Retrofit", "token viewModel: $t")
            }

        })
    }
}