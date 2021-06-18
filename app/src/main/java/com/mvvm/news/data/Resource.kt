package com.mvvm.News.data

import com.mvvm.News.data.common.model.ErrorObject

data class Resource<out T>(
    val status: StateData.DataStatus,
    val data: T?,
    val error: ErrorObject?
) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(StateData.DataStatus.SUCCESS, data, null)
        }

        fun <T> error(error: ErrorObject, data: T?): Resource<T> {
            return Resource(StateData.DataStatus.ERROR, data, error)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(StateData.DataStatus.LOADING, data, null)
        }
    }
}