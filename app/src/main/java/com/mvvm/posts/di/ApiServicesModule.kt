package com.mvvm.posts.di

import com.mvvm.posts.data.api.PostsApiService
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
    fun providePostsApiService(
        @MainClient retrofit: Retrofit
    ): PostsApiService = retrofit.create(PostsApiService::class.java)

}