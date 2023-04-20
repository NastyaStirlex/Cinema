package com.nastirlex.cinema.data.di

import android.content.Context
import com.nastirlex.cinema.data.di.interceptors.AuthorizationInterceptor
import com.nastirlex.cinema.data.di.interceptors.RefreshTokenAuthenticator
import com.nastirlex.cinema.data.di.interceptors.getLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun getTokenOkHttpClient(context: Context): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .authenticator(RefreshTokenAuthenticator(context))
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()