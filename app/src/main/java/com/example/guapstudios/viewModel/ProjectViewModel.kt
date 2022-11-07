package com.example.guapstudios.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guapstudios.data.RetrofitClient
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.emptities.Studio
import com.example.guapstudios.data.modelForJSON.*
import com.example.guapstudios.data.retrofitService.ProjectRetrofitService
import com.example.guapstudios.data.retrofitService.StudioRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectViewModel : ViewModel() {

    private val _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>>
        get() = _projects

    private val _stateLoading = MutableLiveData<StateForRetrofit>(StateForRetrofit.Empty)
    val stateLoading: LiveData<StateForRetrofit>
        get() = _stateLoading

    private val currentStudio = MutableLiveData<Studio>()

    val clientProject =
        RetrofitClient.getRetrofitService().create(ProjectRetrofitService::class.java)
    val clientStudio = RetrofitClient.getRetrofitService().create(StudioRetrofitService::class.java)

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

    fun getProjectsInStudious(typeStudio: String) {
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

    fun addProjectInStudious(projectReceiveModel: ProjectReceiveModel) {
        _stateLoading.value = StateForRetrofit.Loading

        clientProject.addProject(projectReceiveModel).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                getProjectsInStudious(projectReceiveModel.studio)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _stateLoading.value = StateForRetrofit.Error(t.message)
            }

        })
    }

    fun updateProjectInStudious(projectUpdateReceiveModel: ProjectUpdateReceiveModel) {
        _stateLoading.value = StateForRetrofit.Loading

        clientProject.updateProject(projectUpdateReceiveModel).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _stateLoading.value = StateForRetrofit.Loaded
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _stateLoading.value = StateForRetrofit.Error(t.message)
            }
        })
    }

    fun deleteProject(projectDeleteReceiveModel: ProjectDeleteReceiveModel) {

        clientProject.deleteProject(projectDeleteReceiveModel).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _stateLoading.value = StateForRetrofit.Loaded
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _stateLoading.value = StateForRetrofit.Error(t.message)
            }

        })
    }
}