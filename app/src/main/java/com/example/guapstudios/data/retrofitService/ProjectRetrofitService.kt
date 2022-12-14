package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.modelForJSON.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProjectRetrofitService {
    @POST("/project/get")
    fun getProjects(@Body projects: ListStringReceiveModel): Call<ListResponceModel<Project>>

    @POST("/project/add")
    fun addProject(@Body project: ProjectReceiveModel): Call<StringResponceModel>

    @POST("/project/update")
    fun updateProject(@Body projectUpdateReceiveModel: ProjectUpdateReceiveModel): Call<StringResponceModel>

    @POST("/project/delete")
    fun deleteProject(@Body project: ProjectDeleteReceiveModel): Call<StringResponceModel>
}