package com.mvvm.news.di

import com.mvvm.news.domain.NewsRepository
import com.mvvm.news.data.News.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(repository : NewsRepositoryImpl) : NewsRepository


}