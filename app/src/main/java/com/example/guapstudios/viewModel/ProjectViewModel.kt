package com.example.guapstudios.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.emptities.Studio
import com.example.guapstudios.data.modelForJSON.ListResponceModel
import com.example.guapstudios.data.modelForJSON.ListStringReceiveModel
import com.example.guapstudios.data.retrofitService.ProjectRetrofitService
import com.example.guapstudios.data.retrofitService.StudioRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectViewModel : ViewModel() {

    private val  _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>>
        get() = _projects

    private val currentStudio = MutableLiveData<Studio>()

    val clientProject = RetrofitClient.getRetrofitService().create(ProjectRetrofitService::class.java)
    val clientStudio = RetrofitClient.getRetrofitService().create(StudioRetrofitService::class.java)

    fun getStudioInformation(typeStudio: String) {
        clientStudio.getStudio(typeStudio).enqueue(object : Callback<Studio> {
            override fun onResponse(call: Call<Studio>, response: Response<Studio>) {

            }

            override fun onFailure(call: Call<Studio>, t: Throwable) {
                //ERROR
            }

        })
    }

    fun getProjectsInStudiou(typeStudio: String) {
        getStudioInformation(typeStudio)

        currentStudio.observeForever {
            it?.let { studio ->
                clientProject.getProjects(ListStringReceiveModel(it.projects))
                    .enqueue(object : Callback<ListResponceModel<Project>> {
                        override fun onResponse(
                            call: Call<ListResponceModel<Project>>,
                            response: Response<ListResponceModel<Project>>
                        ) {
                            _projects.value = response.body()?.let { projects ->
                                projects.tasks
                            }
                        }

                        override fun onFailure(
                            call: Call<ListResponceModel<Project>>,
                            t: Throwable
                        ) {
                            //ERROR
                        }

                    })
            }
        }
    }
}