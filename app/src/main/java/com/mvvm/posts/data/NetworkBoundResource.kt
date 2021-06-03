package com.mvvm.posts.data

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mvvm.posts.data.common.model.ErrorObject
import com.mvvm.posts.data.common.model.RemoteError
import com.mvvm.posts.util.filterError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Generic class it's job to make a close cycle of Fetch, Sync, Return data between API and Local DB
 *
 * @param ResultType the returned type from DB
 * @param ResponseType the returned type from API call
 *
 * @MainThread -> this method or class must call from MainThread only
 * @WorkerThread -> this method or class must call from BackgroundThread only
 *
 * @param coroutineScope the global scope injected from [CoroutinesModule]
 * by default scope running all operation in the background thread.
 *
 * @param mainDispatcher the main thread dispatcher injected from [CoroutinesModule]
 * we need this dispatcher to run operations need to be called on MainThread
 */
abstract class NetworkBoundResource<ResultType, ResponseType : Any>
@MainThread constructor(
    private val coroutineScope: CoroutineScope,
    private val mainDispatcher: CoroutineDispatcher
) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        // Start with Loading
        result.value = Resource.loading(null)

        coroutineScope.launch(mainDispatcher) {
            val dbSource = loadFromDb()
            result.addSource(dbSource) { data ->
                result.removeSource(dbSource)
                // Check if forceUpdate
                val shouldUpdate = forceUpdate(data)
                Log.d(TAG, "Should I forceUpdate? $shouldUpdate")

                if (shouldUpdate) {
                    Log.d(TAG, "Data should fetch from remote")
                    fetchFromNetwork(dbSource)
                } else {
                    Log.d(TAG, "Data loaded from DB")
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) result.value = newValue

    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        coroutineScope.launch {
            val apiResponse = createCall()

            if (apiResponse is NetworkResponse.Success) {
                Log.d(TAG, "Data loaded from and synced from API successfully")

                saveCallResult(apiResponse.body)

                coroutineScope.launch(mainDispatcher) {
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
            } else {
                apiResponse.filterError()?.let { error ->
                    handleResponseError(dbSource, error)
                    Log.d(TAG, "Network error -> ${error.message}")
                }
            }
        }
    }

    private fun handleResponseError(dbSource: LiveData<ResultType>, error: ErrorObject) {
        coroutineScope.launch(mainDispatcher) {
            if (shouldDisplayLocalVersionWhenError()) {
                // Display error popUp with local version of data
                result.addSource(dbSource) { newData ->
                    setValue(Resource.error(error, newData))
                }
            } else {
                // Display error popUp only
                setValue(Resource.error(error, null))
            }
        }
    }

    @WorkerThread
    protected abstract suspend fun saveCallResult(item: ResponseType)

    @MainThread
    protected abstract fun forceUpdate(data: ResultType?): Boolean

    @MainThread
    protected abstract fun shouldDisplayLocalVersionWhenError(): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract suspend fun createCall(): NetworkResponse<ResponseType, RemoteError>

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    companion object {
        private const val TAG = "NetworkBoundResource"
    }

}