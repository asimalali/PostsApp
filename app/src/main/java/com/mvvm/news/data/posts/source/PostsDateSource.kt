package com.mvvm.news.data.News

import com.mvvm.news.data.common.model.CallResult
import com.mvvm.news.domain.model.News
import com.mvvm.news.domain.model.NewsResponse


interface NewsLocalSource {
  suspend fun saveNews()
}

interface NewsRemoteSource {
  suspend fun getNews() : CallResult<NewsResponse>
}