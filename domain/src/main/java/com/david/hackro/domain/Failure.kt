package com.david.hackro.domain

sealed class Failure {

    class GenericError(val exception: Exception) : Failure()
    object CustomError : Failure()
    object NetworkConnection : Failure()
}
