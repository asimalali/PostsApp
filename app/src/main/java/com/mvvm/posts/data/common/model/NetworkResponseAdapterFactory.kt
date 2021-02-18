package com.mvvm.posts.data.common.model

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType : Type,
        annotations : Array<Annotation>,
        retrofit : Retrofit
    ) : CallAdapter<*, *>? {

        // suspend functions wrap the response type in `Call`
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            ""
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not NetworkResponse then we can't handle this type, so we return null
        if (getRawType(responseType) != CallResult::class.java) {
            return null
        }

        // the response type is NetworkResponse and should be parameterized
        check(responseType is ParameterizedType) { "" }

        val successBodyType = getParameterUpperBound(0, responseType)


        val errorBodyConverter : Converter<ResponseBody, RemoteError> =
            retrofit.responseBodyConverter(RemoteError::class.java, annotations)

        return NetworkResponseAdapter<Any>(successBodyType, errorBodyConverter)
    }
}