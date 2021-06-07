package com.mvvm.posts.data.common.model

import com.mvvm.posts.data.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

//class NetworkResponseAdapter<S : Any>(
//    private val successType : Type,
//    private val errorBodyConverter : Converter<ResponseBody, RemoteError>
//) : CallAdapter<S, Call<CallResult<S>>> {
//
//    override fun responseType() : Type = successType
//
//    override fun adapt(call : Call<S>) : Call<CallResult<S>> {
//        return NetworkResponseCall(
//            call,
//            errorBodyConverter
//        )
//    }
//}



class NetworkResponseAdapter<S : Any, E : Any>(
        private val successType: Type,
        private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<NetworkResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(
                call,
                errorBodyConverter
        )
    }
}