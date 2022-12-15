package com.example.instagramclone.util

sealed class ScreenState<out T> {
    object Empty : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
    class Failure(val throwable: Throwable) : ScreenState<Nothing>()
    class Success<T>(val data: T) : ScreenState<T>()
}