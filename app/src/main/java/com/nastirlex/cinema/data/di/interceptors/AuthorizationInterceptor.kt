package com.nastirlex.cinema.data.di.interceptors

import android.content.Context
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val context: Context) : Interceptor {

    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header("Authorization") == null) {
            builder.addHeader(
                "Authorization",
                "Bearer ${jwtRepositoryImpl.getAccessToken(context)}"
            )
        }

        return chain.proceed(builder.build())
    }
}