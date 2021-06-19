package com.mvvm.news.di

import com.mvvm.news.data.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiServicesModule {

    @Singleton
    @Provides
    fun provideNewsApiService(
        @MainClient retrofit: Retrofit
    ): NewsApiService = retrofit.create(NewsApiService::class.java)

}