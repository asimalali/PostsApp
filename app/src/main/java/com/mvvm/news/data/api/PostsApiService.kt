package com.mvvm.News.data.api

import com.mvvm.News.data.NetworkResponse
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.data.common.model.RemoteError
import com.mvvm.News.domain.model.NewsItem
import com.mvvm.News.domain.model.NewsResponse
import retrofit2.http.*


interface NewsApiService {

  @GET("News")
  suspend fun getNews() : CallResult<List<NewsItem>>


  @GET("News")
  suspend fun getNewNews() : NetworkResponse<List<NewsItem>, RemoteError>

}
