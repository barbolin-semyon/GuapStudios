package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.modelForJSON.ListResponceModel
import com.example.guapstudios.data.modelForJSON.ListStringReceiveModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProjectRetrofitService {
    @GET
    fun getProjects(@Body projects: ListStringReceiveModel): Call<ListResponceModel<Project>>
}