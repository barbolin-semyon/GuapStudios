package com.example.guapstudios.viewModel

sealed class StateForRetrofit {
    object Empty : StateForRetrofit()
    object Loading : StateForRetrofit()
    object Loaded : StateForRetrofit()
    class Error(message: String?) : StateForRetrofit()
}
