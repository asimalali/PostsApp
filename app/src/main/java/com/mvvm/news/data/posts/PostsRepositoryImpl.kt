package com.mvvm.News.data.News


import androidx.lifecycle.LiveData
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.domain.model.NewsItem
import com.mvvm.News.domain.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
  private val NewsRemoteSource : NewsRemoteSource,
  private val NewsLocalSource : NewsLocalSource
) : NewsRepository {


  override suspend fun getNews(): CallResult<List<NewsItem>> {
    return NewsRemoteSource.getNews()
  }

  override suspend fun getLocalNews(): LiveData<List<NewsItem>> {
    return NewsLocalSource.getLocalNews()
  }
}
