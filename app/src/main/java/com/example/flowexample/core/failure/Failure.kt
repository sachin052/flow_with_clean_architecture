package com.example.flowexample.core.failure

sealed class Failure {
    object NetworkFailure : Failure()
    class ServerFailure(val errorCode: Int, val errorMessage: String) : Failure()
    object ParseFailure : Failure()
    object AuthFailure : Failure()

    object NotFound : Failure()

    data class Unknown(val exception: Exception) : Failure()

    /**
     * More feature specific failures will be added here
     */

    abstract class FeatureFailure : Failure()
}