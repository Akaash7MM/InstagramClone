package com.example.domain.util

sealed class Resource<out T> {
    class Success<T>(val result: T) : Resource<T>()
    class Failure(val throwable: Throwable) : Resource<Nothing>()
}
