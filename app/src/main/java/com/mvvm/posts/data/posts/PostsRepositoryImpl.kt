package com.mvvm.posts.data.posts


import androidx.lifecycle.LiveData
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.domain.model.PostItem
import com.mvvm.posts.domain.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepositoryImpl @Inject constructor(
  private val postsRemoteSource : PostsRemoteSource,
  private val postsLocalSource : PostsLocalSource
) : PostsRepository {


  override suspend fun getPosts(): CallResult<List<PostItem>> {
    return postsRemoteSource.getPosts()
  }

  override suspend fun getLocalPosts(): LiveData<List<PostItem>> {
    return postsLocalSource.getLocalPosts()
  }
}
