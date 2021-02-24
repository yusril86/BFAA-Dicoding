package com.yusdroid.bfaapps.ui.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusdroid.bfaapps.data.api.ApiConfig
import com.yusdroid.bfaapps.data.model.SearchResponse
import com.yusdroid.bfaapps.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class UserActivityViewModel : ViewModel() {

    private val dataUsers = MutableLiveData<List<UserResponse>>()
    private val dataSearch = MutableLiveData<SearchResponse>()

    fun fetchDataUsers() {
        val dataServer = ApiConfig.apiServices
        dataServer.getUsers().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                dataUsers.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                dataUsers.postValue(null)
                t.localizedMessage
            }
        })
    }

    fun getDataUsers(): MutableLiveData<List<UserResponse>> {
        return dataUsers
    }

    fun searchUser(username: String) {
        val dataServe = ApiConfig.apiServices
        dataServe.searchUsers(username).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                try {
                  /*  val dataArray = response.body()?.items as ArrayList<UserResponse>
                    dataUsers.postValue(response.body()?.items)*/

                  /*  val mUser = mutableListOf<UserResponse>()
                    val dataArray = response.body()?.items as ArrayList<UserResponse>
                    for (data in mUser){
                        mUser.addAll(dataArray)
                    }
                    dataUsers.postValue(mUser)*/
                    dataUsers.postValue(response.body()?.items)
                }catch (e : Exception){
                    e.stackTrace
                    Log.e("errorko", "username: $username cause: $e")
                    dataUsers.postValue(null)
                }
//               fetchDataUsers()

            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.localizedMessage
            }
        })
    }

    fun getSearchUser() : MutableLiveData<SearchResponse>{
        return  dataSearch
    }
}