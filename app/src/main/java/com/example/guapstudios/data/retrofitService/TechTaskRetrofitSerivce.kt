package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.modelForJSON.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TechTaskRetrofitSerivce {

    @POST("project/tasks")
    fun getTasks(@Body tasks: ListStringReceiveModel)

    @POST("/project/tasks/add")
    fun addTask(@Body task: CreateTaskReceiveModel): Call<StringResponceModel>

     @POST("/project/tasks/delete")
    fun deleteProject(@Body model: DeleteTaskReceiveModel): Call<StringResponceModel>
}