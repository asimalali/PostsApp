package com.mvvm.posts.data.mappers

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import com.mvvm.posts.data.NetworkResponse
import com.mvvm.posts.data.common.model.ErrorObject
import com.mvvm.posts.data.common.model.RemoteError

//fun NetworkResponse.ApiError<RemoteError>.toErrorObject() = ErrorObject(
//        code = this.body.errors?.get(0)?.code ?: this.code,
//        message = if (this.body.errors?.get(0)?.message.isNullOrEmpty()) "" else this.body.errors?.get(0)?.message,
//)

fun NetworkResponse.NetworkError.toErrorObject() = ErrorObject(
        code = -1,
        message = "NetworkError"
)

fun NetworkResponse.UnknownError.toErrorObject() = ErrorObject(
        code = this.code,
        message = if (this.error?.localizedMessage.isNullOrEmpty()) "" else this.error?.localizedMessage
)


fun NetworkResponse.ApiError<RemoteError>.toErrorObject() = ErrorObject(
        code = -1,
        message ="ApiError"
)
