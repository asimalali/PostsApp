package com.mvvm.news.common.state

import androidx.annotation.Keep
import com.mvvm.news.data.common.model.ErrorObject

@Keep
sealed class UiState<T> {

  /**
   * Show normal loading progress bar.
   */
  class Loading<T> : UiState<T>()

  data class Success<T>(val data : T) : UiState<T>()

  data class Error<T>(
    val error : ErrorObject
  ) : UiState<T>()

  class Empty<T>(val msgResId: Int = 0): UiState<T>()


  /**
   * Mainly used in endless scroller of the recycler view to show bottom loading item in the list.
   */
  class LoadMore<T> : UiState<T>()
}