package com.mvvm.posts.di

import android.content.Context
import com.mvvm.posts.data.api.PostsApiService
import com.mvvm.posts.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

}