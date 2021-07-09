package com.example.flowexample.core.helpers


import com.example.flowexample.core.failure.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> safeFlowBuilder(call: suspend () -> Response<T>): Flow<Either<Failure, T>> {
    return flow {
        // invoking suspend
        val response = call.invoke()
        if (response.isSuccessful) {
            emit(Either.Right(response.body() ?: return@flow))
        } else {
            emit(left(Failure.ServerFailure(response.code(), response.errorBody().toString())))
        }

    }.catch { e ->
        emit(Either.Left(Failure.ServerFailure(10, e.localizedMessage)))
    }
}
