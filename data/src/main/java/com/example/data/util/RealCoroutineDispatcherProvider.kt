package com.example.data.util

import com.example.domain.util.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RealCoroutineDispatcherProvider : CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher by lazy { Dispatchers.Main }
    override val io: CoroutineDispatcher by lazy { Dispatchers.IO}
    override val default: CoroutineDispatcher by lazy { Dispatchers.Default }
    override val unconfirmed: CoroutineDispatcher by lazy { Dispatchers.Unconfined }
}