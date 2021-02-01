package com.stc.posts.data.common.mappers

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.stc.posts.data.common.model.ErrorObject
import com.stc.posts.data.common.model.RemoteError
import org.xml.sax.ErrorHandler
import java.io.IOException


fun RemoteError.toErrorObject(httpCode : Int) : ErrorObject {
  val error = errors
  return ErrorObject(
    code = error?.code ?: httpCode,
    message = error?.message
  )
}

fun getDefaultErrorObject(code: Int = -1) = ErrorObject(
  code = -1,
  message =" "
)

fun IOException.toErrorObject() =
  ErrorObject(
    code = -1,
    message = ""
  )
