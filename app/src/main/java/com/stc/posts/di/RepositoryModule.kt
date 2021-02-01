package com.stc.posts.di

import com.stc.posts.domain.PostsRepository
import com.stc.posts.data.posts.PostsRepositoryImpl
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
    abstract fun bindPostsRepository(repository : PostsRepositoryImpl) : PostsRepository


}