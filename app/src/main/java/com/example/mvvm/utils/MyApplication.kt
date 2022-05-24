package com.example.mvvm.utils

import android.app.Application
import android.content.Context
import com.example.mvvm.component.AppComponent
import com.example.mvvm.component.DaggerAppComponent
import com.example.mvvm.modules.APIModule


class MyApplication:Application()
{
   companion object {
        var ctx: Context? = null
        lateinit var apiComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()
    }

    fun initDaggerComponent():AppComponent{
        apiComponent =   DaggerAppComponent
            .builder()
            .aPIModule(APIModule(this))
            .build()
        return  apiComponent
    }
}