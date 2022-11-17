package com.example.data.util

import com.example.domain.util.Resource

suspend fun <T> safeResult(block: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(data = block())
    } catch (e: Exception) {
        Resource.Failure(throwable = e)
    }
}
