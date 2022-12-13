package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.modelForJSON.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskRetrofitServivce {

    @POST("project/tasks")
    fun getTasks(@Body tasks: ListStringReceiveModel): Call<ListResponceModel<TaskDTO>>

    @POST("/project/tasks/add")
    fun addTask(@Body task: CreateTaskReceiveModel): Call<StringResponceModel>

     @POST("/project/tasks/delete")
    fun deleteTask(@Body model: DeleteTaskReceiveModel): Call<StringResponceModel>

    @POST
    fun updateIsCheckTask(@Body model: UpdateTaskReceiveModel): Call<StringResponceModel>
}