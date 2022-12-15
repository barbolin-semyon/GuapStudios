package com.example.guapstudios.data.retrofitService

import com.example.guapstudios.data.emptities.TechTask
import com.example.guapstudios.data.modelForJSON.ListStringReceiveModel
import com.example.guapstudios.data.modelForJSON.ProjectDeleteReceiveModel
import com.example.guapstudios.data.modelForJSON.StringResponceModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TechTaskRetrofitService {
    @POST("/tech_task/add")
    fun addTechTask(@Body techTask: TechTask): Call<StringResponceModel>

    @POST("/tech_task/delete")
    fun deleteTechTask(@Body projectDeleteReceiveModel: ProjectDeleteReceiveModel): Call<StringResponceModel>

    @POST("/tech_task/get")
    fun getTechTask(@Body listStringReceiveModel: ListStringReceiveModel): Call<StringResponceModel>
}