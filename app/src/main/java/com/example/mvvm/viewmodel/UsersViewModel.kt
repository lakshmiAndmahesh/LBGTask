package com.example.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.api.NoInternetException
import com.example.mvvm.repositories.AppRepository
import com.example.mvvm.retrofitResponses.ResponseHandler
import com.example.mvvm.retrofitResponses.UserResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

 class UsersViewModel(val appRepository: AppRepository):ViewModel() {
    private var user_data=MutableLiveData<ResponseHandler<UserResponse>>()
    var users: LiveData<ResponseHandler<UserResponse>> = user_data

   fun getUsers() {

       viewModelScope.launch(Dispatchers.IO) {
           user_data.postValue(ResponseHandler.IsLoading)
           try {
               val response = appRepository.getUsers()
               response?.let {
                   if (response.isSuccessful){
                       response.body()?.let {
                           user_data.postValue(ResponseHandler.Success(response.body()!!))
                       }
                   }
               }
           }catch (ex:NoInternetException) {
               user_data.postValue(ResponseHandler.Failure(ex.message.toString()))
           }catch (e:Exception){
               user_data.postValue(ResponseHandler.Failure(e.message.toString()))
           }
       }
   }
}