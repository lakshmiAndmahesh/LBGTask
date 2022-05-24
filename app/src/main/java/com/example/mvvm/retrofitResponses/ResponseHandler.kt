package com.example.mvvm.retrofitResponses

sealed class ResponseHandler<out T>{
    object IsLoading : ResponseHandler<Nothing>()
    class Success<out R>(val value : R):ResponseHandler<R>()
    class Failure<out R>(message : String):ResponseHandler<R>()
}

