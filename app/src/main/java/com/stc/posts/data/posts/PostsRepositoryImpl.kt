package com.stc.posts.data.posts


import com.stc.posts.data.common.model.CallResult
import com.stc.posts.domain.model.Post
import com.stc.posts.domain.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepositoryImpl @Inject constructor(
  private val postsRemoteSource : PostsRemoteSource,
  private val postsLocalSource : PostsLocalSource
) : PostsRepository {


  override suspend fun getPosts(): CallResult<List<Post>> {
    return postsRemoteSource.getPosts()
  }
}
