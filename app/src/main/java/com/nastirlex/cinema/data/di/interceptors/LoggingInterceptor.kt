package com.nastirlex.cinema.data.di.interceptors

import okhttp3.logging.HttpLoggingInterceptor

fun getLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return loggingInterceptor
}