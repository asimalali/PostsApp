package com.mvvm.News.di

import android.content.Context
import com.mvvm.News.data.api.NewsApiService
import com.mvvm.News.data.db.AppDatabase
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
    fun provideNewsApiService(
        @MainClient retrofit: Retrofit
    ): NewsApiService = retrofit.create(NewsApiService::class.java)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

}