package com.mvvm.news.data.api

import com.mvvm.news.data.common.model.CallResult
import com.mvvm.news.domain.model.NewsResponse
import retrofit2.http.*


interface NewsApiService {

  @GET("top-headlines?apiKey=21ee64eb82264752bf4f62e5ac710c00&category=technology")
  suspend fun getNews(): CallResult<NewsResponse>
}
