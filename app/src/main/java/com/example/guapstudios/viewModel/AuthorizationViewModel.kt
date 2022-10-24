package com.example.guapstudios.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.modelForJSON.LoginReciveModel
import com.example.guapstudios.data.modelForJSON.RegisterReciveModel
import com.example.guapstudios.data.modelForJSON.TokenModel
import com.example.guapstudios.data.emptities.User
import com.example.guapstudios.data.retrofitService.AuthorizationRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthorizationViewModel : ViewModel() {

    var user: User? = null
        private set

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
                    user = response.body()
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