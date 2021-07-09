package com.example.flowexample.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowexample.core.failure.Failure
import com.example.flowexample.core.helpers.Either
import com.example.flowexample.core.views.ViewStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

open class MyViewModel : ViewModel() {
    val uiState = MutableLiveData<ViewStatus>()

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    protected val viewModelJob = Job()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    protected val coroutineScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */

    //    helper function to execute the api
    fun <T> executeApi(call: suspend () -> Flow<Either<Failure, T>>): Flow<Either<Failure, T>> {
        return flow {
            uiState.postValue(ViewStatus.Loading)
            call.invoke().collect { value ->

                when (value) {
                    is Either.Left -> {
                        uiState.postValue(ViewStatus.Error("Something went wrong", action = {
                            executeApi(call)
                        }))
                        emit(value = value)
                    }
                    is Either.Right -> {
                        uiState.postValue(ViewStatus.Success)
                        emit(value = value)
                    }
                }
            }

        }
    }


}