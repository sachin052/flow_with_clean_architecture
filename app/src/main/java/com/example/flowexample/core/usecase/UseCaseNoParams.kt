package com.example.flowexample.core.usecase

import com.example.flowexample.core.failure.Failure
import com.example.flowexample.core.helpers.Either
import kotlinx.coroutines.flow.Flow


interface UseCaseNoParams<Result>  {

    suspend operator fun invoke(): Flow<Either<Failure,Result>>
}