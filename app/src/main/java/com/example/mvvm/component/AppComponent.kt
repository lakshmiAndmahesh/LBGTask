package com.example.mvvm.component

import com.example.mvvm.modules.APIModule
import com.example.mvvm.view.UsersActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[APIModule::class])
interface AppComponent {
  fun inject(customersActivity: UsersActivity)
}