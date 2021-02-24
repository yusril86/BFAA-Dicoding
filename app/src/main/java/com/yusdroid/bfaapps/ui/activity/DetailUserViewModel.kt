package com.yusdroid.bfaapps.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusdroid.bfaapps.data.api.ApiConfig
import com.yusdroid.bfaapps.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
     val dataUser = MutableLiveData<UserResponse>()
     val dataUserFollow = MutableLiveData<List<UserResponse>>()


    fun getUser(username:String){
        val dataService = ApiConfig.apiServices.getDetail(username)
        dataService.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                dataUser.postValue(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.localizedMessage
            }
        })
    }

    /*fun getUsersFollow(username: String,follow:String){
        val dataService = ApiConfig.apiServices.getUsersFollow(username,follow)
        dataService.enqueue(object : Callback<List<UserResponse>>{
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                dataUserFollow.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                t.localizedMessage
                dataUserFollow.postValue(null)
            }
        })
    }*/

    fun  getFollowers(username : String){
        val dataService = ApiConfig.apiServices.getUsersFollowers(username)
        dataService.enqueue(object : Callback<List<UserResponse>>{
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                dataUserFollow.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                t.localizedMessage
            }
        })
    }

    fun getFollowing(username: String){
        val dataService = ApiConfig.apiServices.getUsersFollowing(username)
        dataService.enqueue(object : Callback<List<UserResponse>>{
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                dataUserFollow.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                t.localizedMessage
            }
        })
    }

    fun getDataUsers(): LiveData<List<UserResponse>> {
        return dataUserFollow
    }
}