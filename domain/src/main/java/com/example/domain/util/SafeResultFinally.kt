package com.example.domain.util

suspend fun <T> safeResultFinally(block: suspend () -> T, finally: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(data = block())
    } catch (e: Exception) {
        Resource.Failure(throwable = e)
    } finally {
        finally()
    }
}
