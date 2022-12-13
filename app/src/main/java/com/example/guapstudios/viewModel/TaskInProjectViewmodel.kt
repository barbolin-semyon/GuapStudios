package com.example.guapstudios.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.modelForJSON.*
import com.example.guapstudios.data.retrofitService.TaskRetrofitServivce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskInProjectViewmodel : ViewModel() {
    private val _tasks = MutableLiveData<List<TaskDTO>>()
    val tasks: LiveData<List<TaskDTO>>
        get() = _tasks

    val clientTask =
        RetrofitClient.getRetrofitService().create(TaskRetrofitServivce::class.java)

    fun getTasks(index: List<String>) {

        clientTask.getTasks(ListStringReceiveModel(index.toTypedArray()))
            .enqueue(object : Callback<ListResponceModel<TaskDTO>> {
                override fun onResponse(
                    call: Call<ListResponceModel<TaskDTO>>,
                    response: Response<ListResponceModel<TaskDTO>>
                ) {
                    _tasks.value = response.body()?.let { tasks ->
                        tasks.tasks
                    }
                }

                override fun onFailure(
                    call: Call<ListResponceModel<TaskDTO>>,
                    t: Throwable
                ) {
                    //ERROR
                }

            })
    }

    fun addTaskInProject(model: CreateTaskReceiveModel) {
        clientTask.addTask(model).enqueue(object :
            Callback<StringResponceModel> {
            override fun onResponse(
                call: Call<StringResponceModel>,
                response: Response<StringResponceModel>
            ) {

            }

            override fun onFailure(call: Call<StringResponceModel>, t: Throwable) {

            }

        })
    }

    fun changeIsCheck(model: UpdateTaskReceiveModel) {

        clientTask.updateIsCheckTask(model).enqueue(object :
            Callback<StringResponceModel> {
            override fun onResponse(
                call: Call<StringResponceModel>,
                response: Response<StringResponceModel>
            ) {
                tasks.value?.map { it.id }?.let { getTasks(it) }
            }

            override fun onFailure(call: Call<StringResponceModel>, t: Throwable) {

            }
        })
    }

    fun deleteTask(model: DeleteTaskReceiveModel) {

        clientTask.deleteTask(model).enqueue(object :
            Callback<StringResponceModel> {
            override fun onResponse(
                call: Call<StringResponceModel>,
                response: Response<StringResponceModel>
            ) {
                _tasks.value = _tasks.value?.filter { it.id != model.taskId }
            }

            override fun onFailure(call: Call<StringResponceModel>, t: Throwable) {

            }

        })
    }
}
