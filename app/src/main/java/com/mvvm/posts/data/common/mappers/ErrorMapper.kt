package com.mvvm.posts.data.common.mappers


import com.mvvm.posts.MyApp
import com.mvvm.posts.R
import com.mvvm.posts.data.common.ERROR_NETWORK
import com.mvvm.posts.data.common.ERROR_UNKNOWN
import com.mvvm.posts.data.common.model.ErrorObject
import com.mvvm.posts.data.common.model.RemoteError
import java.io.IOException


fun RemoteError.toErrorObject(httpCode : Int) : ErrorObject {
  val error = errors
  return ErrorObject(
    code = error?.code ?: httpCode,
    message = error?.message
  )
}

fun getDefaultErrorObject(code: Int = ERROR_UNKNOWN) = ErrorObject(
  code = ERROR_UNKNOWN,
  message = MyApp.getContext().getString(R.string.default_error)

)

fun IOException.toErrorObject() =
  ErrorObject(
    code = ERROR_NETWORK,
    message = MyApp.getContext().getString(R.string.no_internet_connection)
  )
