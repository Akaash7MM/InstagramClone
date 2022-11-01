package com.example.data.util

import com.example.data.util.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", API_KEY)

        return chain.proceed(request.build())
    }
}
