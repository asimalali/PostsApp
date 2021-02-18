package com.mvvm.posts.data.common.model

import com.mvvm.posts.data.common.mappers.getDefaultErrorObject
import com.mvvm.posts.data.common.mappers.toErrorObject
import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


const val ERROR_UNKNOWN = -1

class NetworkResponseCall<S : Any>(
    private val delegate : Call<S>,
    private val errorConverter : Converter<ResponseBody, RemoteError>
) : Call<CallResult<S>> {

    override fun enqueue(callback : Callback<CallResult<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call : Call<S>, response : Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(CallResult.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(CallResult.Fail(getError(code, error)))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex : Exception) {
                            null
                        }
                    }
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(CallResult.Fail(getError(code, errorBody)))
                    )
                }
            }

            override fun onFailure(call : Call<S>, throwable : Throwable) {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(
                        CallResult.Fail(
                            getError(
                              ERROR_UNKNOWN, throwable
                            )
                        )
                    )
                )
//                Timber.e(throwable)
            }
        })
    }

    private fun getError(
        errorCode : Int,
        anonymous : Any?
    ) : ErrorObject {
        return when (anonymous) {
            is RemoteError -> anonymous.toErrorObject(errorCode)
            is SocketTimeoutException,
            is UnknownHostException,
            is ConnectException -> (anonymous as IOException).toErrorObject()
            else -> getDefaultErrorObject()
        }
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(
        delegate.clone(),
        errorConverter
    )

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute() : Response<CallResult<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request() : Request = delegate.request()

    override fun timeout() : Timeout = delegate.timeout()
}