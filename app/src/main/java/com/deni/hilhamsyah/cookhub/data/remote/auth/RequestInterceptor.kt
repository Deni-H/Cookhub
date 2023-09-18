package com.deni.hilhamsyah.cookhub.data.remote.auth

import com.deni.hilhamsyah.cookhub.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor (
    private val authRepository: AuthRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val token = runBlocking(Dispatchers.IO) {
            authRepository.getIdToken(false)
        }

        val request = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()

        chain.proceed(request)
    }
}