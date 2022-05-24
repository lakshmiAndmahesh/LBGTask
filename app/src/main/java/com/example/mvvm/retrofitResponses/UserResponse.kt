package com.example.mvvm.retrofitResponses

data class UserResponse(
    val page:Int,
    val per_page:Int,
    val total:Int,
    val data:List<User>)
