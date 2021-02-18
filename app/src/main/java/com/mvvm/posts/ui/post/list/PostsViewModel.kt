package com.mvvm.posts.ui.post.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mvvm.posts.common.base.fragment.BaseViewModel
import com.mvvm.posts.common.state.SingleLiveEvent
import com.mvvm.posts.data.common.model.CallResult
import com.mvvm.posts.data.common.model.ErrorObject
import com.mvvm.posts.domain.PostsRepository
import com.mvvm.posts.domain.model.Post
import kotlinx.coroutines.launch
import timber.log.Timber


class PostsViewModel @ViewModelInject constructor(
    private val postsRepository : PostsRepository
) : BaseViewModel() {

    val postsListLiveData = SingleLiveEvent<List<Post>?>()
    val isDataAvailable : LiveData<Boolean?> = postsListLiveData.map {
        it?.isNotEmpty()
    }
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