package com.example.mvvm.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.repositories.AppRepository
import com.example.mvvm.viewmodel.UsersViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyViewModelFactory
@Inject
constructor(private val repository: AppRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UsersViewModel::class.java!!))
        {
            UsersViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}