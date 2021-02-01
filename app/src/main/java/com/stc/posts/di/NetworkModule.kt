package com.stc.posts.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stc.posts.BuildConfig
import com.stc.posts.data.common.model.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


// HttpClient
val READ_TIMEOUT = if (BuildConfig.DEBUG) 80 else 60
val CALL_TIMEOUT = if (BuildConfig.DEBUG) 80 else 60
val CONNECTION_TIMEOUT = if (BuildConfig.DEBUG) 80 else 60
@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

  @Provides
  fun provideOkHttpClient() : OkHttpClient = OkHttpClient.Builder().build()

  @Provides
  fun provideLoggingInterceptor() =
    HttpLoggingInterceptor().apply {
      level =

         HttpLoggingInterceptor.Level.BODY
    }


  @MainClient
  @Provides
  fun provideMainOkHttpClient(
    upstreamClient : OkHttpClient,
    httpLoggingInterceptor : HttpLoggingInterceptor
  ) : OkHttpClient {
    return getHttpClientBuilder(upstreamClient)
      .addNetworkInterceptor(httpLoggingInterceptor)
      .build()
  }

  private fun getHttpClientBuilder(
    upstreamClient : OkHttpClient
  ) : OkHttpClient.Builder {
    return upstreamClient.newBuilder()
      .callTimeout(CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
      .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
      .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
  }

  @MainClient
  @Provides
  fun provideMainBaseUrl() = BuildConfig.BASE_URL

  @MainClient
  @Provides
  fun provideMainRetrofit(
    @MainClient okhttpClient : OkHttpClient,
    converterFactory : GsonConverterFactory,
    networkResponseAdapterFactory : NetworkResponseAdapterFactory,
    @MainClient baseURL : String
  ) = createRetrofit(
    okhttpClient,
    converterFactory,
    networkResponseAdapterFactory,
    baseURL
  )

  private fun createRetrofit(
    okhttpClient : OkHttpClient,
    converterFactory : GsonConverterFactory,
    networkResponseAdapterFactory : NetworkResponseAdapterFactory,
    baseURL : String
  ) : Retrofit = Retrofit.Builder()
    .baseUrl(baseURL)
    .addConverterFactory(converterFactory)
    .addCallAdapterFactory(networkResponseAdapterFactory)
    .client(okhttpClient)
    .build()

  @Provides
  @Singleton
  fun provideGson() : Gson {
    return GsonBuilder()
      .serializeNulls()
      .create()
  }

  @Provides
  @Singleton
  fun provideGsonConverterFactory(gson : Gson) : GsonConverterFactory =
    GsonConverterFactory.create(gson)

  @Provides
  @Singleton
  fun provideNetworkResponseAdapterFactory() : NetworkResponseAdapterFactory =
    NetworkResponseAdapterFactory()
}