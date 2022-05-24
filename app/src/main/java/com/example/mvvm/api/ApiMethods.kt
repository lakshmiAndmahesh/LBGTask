package com.example.mvvm.api

import com.example.mvvm.retrofitResponses.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiMethods {
    @GET("api/users")
    suspend fun getUsers(): Response<UserResponse>
}