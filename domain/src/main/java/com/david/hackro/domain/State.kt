package com.david.hackro.domain

sealed class State {
    object Loading : State()
    object Empty : State()
    data class Failed(val failure: Failure) : State()

    data class Success(var data: Any) : State() {
        inline fun <reified T> responseTo() = data as T
    }
}
