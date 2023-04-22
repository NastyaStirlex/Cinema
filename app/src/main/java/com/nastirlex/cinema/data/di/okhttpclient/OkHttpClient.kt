package com.nastirlex.cinema.data.di

import android.content.Context
import com.nastirlex.cinema.data.di.interceptors.AuthorizationInterceptor
import com.nastirlex.cinema.data.di.interceptors.getLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun getOkHttpClient(context: Context): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor(context))
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()