package com.mvvm.news.ui.News.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mvvm.news.common.base.fragment.BaseViewModel
import com.mvvm.news.common.state.UiState
import com.mvvm.news.data.common.model.CallResult
import com.mvvm.news.data.common.model.ErrorObject
import com.mvvm.news.domain.NewsRepository
import com.mvvm.news.domain.model.News
import com.mvvm.news.domain.model.NewsResponse
import kotlinx.coroutines.launch
import timber.log.Timber


class NewsViewModel @ViewModelInject constructor(
    private val NewsRepository : NewsRepository
) : BaseViewModel() {



    private val _uiState = MutableLiveData<UiState<NewsResponse>>()
    val uiState : MutableLiveData<UiState<NewsResponse>> = _uiState

    init {
        getNews()
    }
    private fun getNews() {
            viewModelScope.launch {
                _uiState.postValue(UiState.Loading())
                val result = NewsRepository.getNews()
                when (result) {
                    is CallResult.Success -> {
                        _uiState.postValue(UiState.Success(result.data!!))
                        Timber.d("SIZE: ${result.data.articles.size}")
                    }
                    is CallResult.Fail -> {
                        _uiState.postValue(UiState.Error(result.error))
                    }
                }
            }
    }

    sealed class NewsState {
        object Loading : NewsState()
        data class Error(val error : ErrorObject) : NewsState()
        data class Success(val News : List<News>) : NewsState()

    }

}