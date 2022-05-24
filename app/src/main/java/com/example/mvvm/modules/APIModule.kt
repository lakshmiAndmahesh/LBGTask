package com.example.mvvm.modules

import android.content.Context
import com.example.mvvm.api.ApiMethods
import com.example.mvvm.api.Constants
import com.example.mvvm.api.NetworkConnetionInterceptor
import com.example.mvvm.repositories.AppRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class APIModule(context: Context) {

    @Singleton
    @Provides
    fun provaidInterceptor():NetworkConnetionInterceptor{
        return NetworkConnetionInterceptor()
    }

    @Singleton
    @Provides
    fun provideOKHttpClient(networkConnetionInterceptor: NetworkConnetionInterceptor):OkHttpClient{
        return  OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .addInterceptor(networkConnetionInterceptor)
            .connectTimeout(1200,TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return  GsonConverterFactory.create()
    }
    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient):Retrofit{

        return     Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiMethods(retrofit: Retrofit):ApiMethods{
        return retrofit.create(ApiMethods::class.java);
    }

    @Singleton
    @Provides
    fun provideRepository(apiMethods: ApiMethods):AppRepository
    {
        return AppRepository(apiMethods)
    }

}