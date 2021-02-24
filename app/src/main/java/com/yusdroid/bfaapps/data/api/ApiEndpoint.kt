package com.yusdroid.bfaapps.data.api

import com.yusdroid.bfaapps.BuildConfig
import com.yusdroid.bfaapps.data.model.SearchResponse
import com.yusdroid.bfaapps.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("users")
    fun getUsers() : Call<List<UserResponse>>

    @GET("search/users")
    @Headers(BuildConfig.API_KEY)
    fun searchUsers(
        @Query("q") username :String
    ) : Call<SearchResponse>

    @GET("users/{username}")
    fun getDetail(
            @Path("username") username: String
    ): Call<UserResponse>

    @GET("users/{username}/followers")
    @Headers(BuildConfig.API_KEY)
    fun getUsersFollowers(
            @Path("username") username: String
    ): Call<List<UserResponse>>

    @GET("users/{username}/following")
    @Headers(BuildConfig.API_KEY)
    fun getUsersFollowing(
            @Path("username") username: String
    ): Call<List<UserResponse>>


}
