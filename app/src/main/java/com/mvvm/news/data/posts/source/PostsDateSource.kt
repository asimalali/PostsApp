package com.mvvm.News.data.News

import androidx.lifecycle.LiveData
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.domain.model.NewsItem


interface NewsLocalSource {
  suspend fun saveNews()
  suspend fun getLocalNews() : LiveData<List<NewsItem>>
}

interface NewsRemoteSource {
  suspend fun getNews() : CallResult<List<NewsItem>>
}