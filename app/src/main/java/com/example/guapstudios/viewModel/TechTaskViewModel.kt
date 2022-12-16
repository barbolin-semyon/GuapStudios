package com.example.guapstudios.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.emptities.Studio
import com.example.guapstudios.data.emptities.TechTask
import com.example.guapstudios.data.modelForJSON.*
import com.example.guapstudios.data.retrofitService.ProjectRetrofitService
import com.example.guapstudios.data.retrofitService.StudioRetrofitService
import com.example.guapstudios.data.retrofitService.TaskRetrofitServivce
import com.example.guapstudios.data.retrofitService.TechTaskRetrofitService
import com.skat.database.tech_task.TechTaskUpdateExecutor
import com.skat.database.tech_task.TechTaskUpdateIsTake
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechTaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<TechTask>>()
    val tasks: LiveData<List<TechTask>>
        get() = _tasks

    private val _isUpdated = MutableLiveData<Boolean>(false)
    val isUpdated: LiveData<Boolean>
        get() = _isUpdated

    val clientTechTask =
        RetrofitClient.getRetrofitService().create(TechTaskRetrofitService::class.java)

    private val clientStudio =
        RetrofitClient.getRetrofitService().create(StudioRetrofitService::class.java)

    private val currentStudio = MutableLiveData<Studio>()

    fun getStudioInformation(typeStudio: String) {
        clientStudio.getStudio(typeStudio).enqueue(object : Callback<Studio> {
            override fun onResponse(call: Call<Studio>, response: Response<Studio>) {
                currentStudio.value = response.body()
            }

            override fun onFailure(call: Call<Studio>, t: Throwable) {
                //ERROR
            }

        })
    }

    fun getTechTaskInStudious(typeStudio: String) {
        getStudioInformation(typeStudio)

        currentStudio.observeForever {
            it?.let { studio ->
                clientTechTask.getTechTask(ListStringReceiveModel(it.tech_task))
                    .enqueue(object : Callback<ListResponceModel<TechTask>> {
                        override fun onResponse(
                            call: Call<ListResponceModel<TechTask>>,
                            response: Response<ListResponceModel<TechTask>>
                        ) {
                            _tasks.value = response.body()?.let { items ->
                                items.tasks
                            }
                        }

                        override fun onFailure(
                            call: Call<ListResponceModel<TechTask>>,
                            t: Throwable
                        ) {
                            //ERROR
                            println()
                        }

                    })
            }
        }
    }

    fun updateIsTakeInTechStudious(updateIsTake: TechTaskUpdateIsTake) {
        clientTechTask.updateIsTakeTechTask(updateIsTake).enqueue(object : Callback<StringResponceModel> {
            override fun onResponse(
                call: Call<StringResponceModel>,
                response: Response<StringResponceModel>
            ) {
                _isUpdated.value = true
            }

            override fun onFailure(call: Call<StringResponceModel>, t: Throwable) {

            }
        })
    }

    fun updateIsTakeInTechStudious(updateExecutor: TechTaskUpdateExecutor) {
        clientTechTask.updateExecutorTechTask(updateExecutor).enqueue(object : Callback<StringResponceModel> {
            override fun onResponse(
                call: Call<StringResponceModel>,
                response: Response<StringResponceModel>
            ) {
                _isUpdated.value = true
            }

            override fun onFailure(call: Call<StringResponceModel>, t: Throwable) {

            }
        })
    }

    fun addTaskInProject(model: TechTask) {
        clientTechTask.addTechTask(model).enqueue(object :
            Callback<StringResponceModel> {
            override fun onResponse(
                call: Call<StringResponceModel>,
                response: Response<StringResponceModel>
            ) {

            }

            override fun onFailure(call: Call<StringResponceModel>, t: Throwable) {
                Log.i("QWE", "loaded task")
            }

        })
    }

    fun deleteTask(model: ProjectDeleteReceiveModel) {

        clientTechTask.deleteTechTask(model).enqueue(object :
            Callback<StringResponceModel> {
            override fun onResponse(
                call: Call<StringResponceModel>,
                response: Response<StringResponceModel>
            ) {
                _tasks.value = _tasks.value?.filter { it.id != model.id }
            }

            override fun onFailure(call: Call<StringResponceModel>, t: Throwable) {

            }

        })
    }
}