package com.mvvm.News.data.common.mappers


import com.mvvm.News.MyApp
import com.mvvm.News.R
import com.mvvm.News.data.common.ERROR_NETWORK
import com.mvvm.News.data.common.ERROR_UNKNOWN
import com.mvvm.News.data.common.model.ErrorObject
import com.mvvm.News.data.common.model.RemoteError
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
