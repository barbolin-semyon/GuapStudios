package com.example.guapstudios.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.emptities.LoginReciveModel
import com.example.guapstudios.data.emptities.TokenModel
import com.example.guapstudios.data.retrofitService.AuthorizationRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthorizationViewModel : ViewModel() {

    private val _isAuthorization = MutableLiveData<Boolean>(false)
    val isAuthorization: LiveData<Boolean>
        get() = _isAuthorization

    fun login(reciveModel: LoginReciveModel) {
        val client = RetrofitClient.getAuthorizationRetrofitService()
            .create(AuthorizationRetrofitService::class.java)

        client.login(reciveModel).enqueue(object : Callback<TokenModel> {
            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                if (response.isSuccessful) {
                    _isAuthorization.value = true
                } else {
                    Log.i("Retrofit", "login viewMOdel error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                Log.i("Retrofit", "login view Model: $t")
            }

        })
    }
}