package com.mvvm.news.data.News


import com.mvvm.news.data.common.model.CallResult
import com.mvvm.news.domain.model.News
import com.mvvm.news.domain.NewsRepository
import com.mvvm.news.domain.model.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
  private val NewsRemoteSource : NewsRemoteSource,
  private val NewsLocalSource : NewsLocalSource
) : NewsRepository {


  override suspend fun getNews(): CallResult<NewsResponse> {
    return NewsRemoteSource.getNews()
  }
}
