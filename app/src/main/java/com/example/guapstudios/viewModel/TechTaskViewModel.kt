package com.example.guapstudios.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.emptities.TechTask
import com.example.guapstudios.data.modelForJSON.*
import com.example.guapstudios.data.retrofitService.TaskRetrofitServivce
import com.example.guapstudios.data.retrofitService.TechTaskRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechTaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<TechTask>>()
    val tasks: LiveData<List<TechTask>>
        get() = _tasks

    val clientTechTask =
        RetrofitClient.getRetrofitService().create(TechTaskRetrofitService::class.java)

    fun getTasks(index: Array<String>) {

        clientTechTask.getTechTask(ListStringReceiveModel(index))
            .enqueue(object : Callback<ListResponceModel<TechTask>> {
                override fun onResponse(
                    call: Call<ListResponceModel<TechTask>>,
                    response: Response<ListResponceModel<TechTask>>
                ) {
                    _tasks.value = response.body()?.let { tasks ->
                        tasks.tasks
                    }
                }

                override fun onFailure(
                    call: Call<ListResponceModel<TechTask>>,
                    t: Throwable
                ) {
                    //ERROR
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