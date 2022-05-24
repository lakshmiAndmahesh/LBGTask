package com.example.mvvm.repositories

import com.example.mvvm.api.ApiMethods

class AppRepository(val apiMethods: ApiMethods) {
   suspend fun getUsers()= apiMethods.getUsers()
}