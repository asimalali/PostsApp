package com.mvvm.News.ui.News.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mvvm.News.common.base.fragment.BaseViewModel
import com.mvvm.News.data.Resource
import com.mvvm.News.data.common.model.CallResult
import com.mvvm.News.data.common.model.ErrorObject
import com.mvvm.News.data.repo.NewsRepo
import com.mvvm.News.domain.NewsRepository
import com.mvvm.News.domain.model.NewsItem
import com.mvvm.News.util.NullableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel @ViewModelInject constructor(
    private val NewsRepository : NewsRepository,
    private val NewsRepo: NewsRepo
) : BaseViewModel() {

    private val _uiState = SingleLiveEvent<PostsState>()
    val uiState : LiveData<PostsState> = _uiState

    init {
        getPosts()
    }
    private fun getPosts() {
        viewModelScope.launch {
            _uiState.postValue(PostsState.Loading)
            val result = postsRepository.getPosts()
            when (result) {
                is CallResult.Success -> {
                    postsListLiveData.postValue(result.data)
                    Timber.d("SIZE: ${result.data!!.size}")
                    _uiState.postValue(PostsState.HideLoading)
                }
                is CallResult.Fail -> {
                    _uiState.postValue(PostsState.Error(result.error))
                }
            }
        }
    }

    sealed class PostsState {
        object Loading : PostsState()
        object HideLoading : PostsState()
        data class Error(val error : ErrorObject) : PostsState()
    }
}