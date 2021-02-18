package com.mvvm.posts.di

import com.mvvm.posts.data.posts.PostsLocalSource
import com.mvvm.posts.data.posts.source.local.PostsLocalSourceImp
import com.mvvm.posts.data.posts.PostsRemoteSource
import com.mvvm.posts.data.posts.source.remote.PostsRemoteSourceImp
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
    abstract fun bindPostsLocalSource(source : PostsLocalSourceImp) : PostsLocalSource

    @Binds
    @Singleton
    abstract fun bindPostsRemoteSource(source : PostsRemoteSourceImp) : PostsRemoteSource

}