package com.mvvm.News.di

import com.mvvm.News.domain.NewsRepository
import com.mvvm.News.data.News.NewsRepositoryImpl
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