package com.mvvm.News.util


import com.mvvm.News.data.NetworkResponse
import com.mvvm.News.data.Resource
import com.mvvm.News.data.common.model.ErrorObject
import com.mvvm.News.data.common.model.RemoteError
import com.mvvm.News.data.mappers.toErrorObject

/**
 * Save boilerplate 'when' statement to convert [NetworkResponse] to [ErrorObject]
 */
fun NetworkResponse<Any, RemoteError>.filterError(): ErrorObject? {
    return when (this) {
        is NetworkResponse.Success -> null
        is NetworkResponse.UnknownError -> this.toErrorObject()
        is NetworkResponse.ApiError -> this.toErrorObject()
        is NetworkResponse.NetworkError -> this.toErrorObject()
    }
}

/**
 * Switch [NetworkResponse] into [Resource]
 */
@Suppress("UNCHECKED_CAST")
fun <T : Any> NetworkResponse<Any, RemoteError>.asResource(): Resource<T> {
    return when (this) {
        is NetworkResponse.Success -> Resource.success(body as T)
        is NetworkResponse.UnknownError -> Resource.error(toErrorObject(), null)
        is NetworkResponse.ApiError -> Resource.error(toErrorObject(), null)
        is NetworkResponse.NetworkError -> Resource.error(toErrorObject(), null)
    }
}