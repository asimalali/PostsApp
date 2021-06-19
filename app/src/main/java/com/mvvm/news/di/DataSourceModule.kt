package com.mvvm.news.di

import com.mvvm.news.data.News.NewsLocalSource
import com.mvvm.news.data.News.source.local.NewsLocalSourceImp
import com.mvvm.news.data.News.NewsRemoteSource
import com.mvvm.news.data.News.source.remote.NewsRemoteSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class DataSourceModule {


    @Binds
    @Singleton
    abstract fun bindNewsLocalSource(source : NewsLocalSourceImp) : NewsLocalSource

    @Binds
    @Singleton
    abstract fun bindNewsRemoteSource(source : NewsRemoteSourceImp) : NewsRemoteSource

}