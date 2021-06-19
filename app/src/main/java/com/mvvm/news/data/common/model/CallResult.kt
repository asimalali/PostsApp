package com.mvvm.news.data.common.model


sealed class CallResult<out T> {

    data class Success<T>(val data : T? = null) : CallResult<T>()
    data class Fail<T>(val error : ErrorObject) : CallResult<T>()


}

