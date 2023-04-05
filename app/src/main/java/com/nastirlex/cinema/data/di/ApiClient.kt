package com.nastirlex.cinema.data.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "http://107684.web.hosting-russia.ru:8000/api/"

    private fun interceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    //GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
                )
            )
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor())
                    .build()
            )
            .build()
    }

    val authApiService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val movieApiService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

    val collectionsApiService: CollectionsService by lazy {
        retrofit.create(CollectionsService::class.java)
    }

}