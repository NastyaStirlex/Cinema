package com.nastirlex.cinema.data.di

import android.content.Context
import com.nastirlex.cinema.data.di.interceptors.AuthorizationInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
                GsonConverterFactory.create()
            )
            .client(
                OkHttpClient.Builder()

                    //.addInterceptor(AuthorizationInterceptor())
                    .addInterceptor(interceptor())
                    .build()
            )
        //.build()
    }

    private lateinit var movieService: MovieService
    private lateinit var authService: AuthService
    private lateinit var episodesService: EpisodesService

    fun getAuthApiService(context: Context): AuthService {
        if (!::authService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(interceptor())
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .build()
                )
                .build()

            authService = retrofit.create(AuthService::class.java)
        }

        return authService
    }

    fun getMovieApiService(context: Context): MovieService {
        if (!::movieService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getOkHttpClient(context))
                .build()

            movieService = retrofit.create(MovieService::class.java)
        }

        return movieService

    }


    fun getEpisodesApiService(context: Context): EpisodesService {
        if (!::episodesService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getOkHttpClient(context))
                .build()

            episodesService = retrofit.create(EpisodesService::class.java)
        }

        return episodesService
    }

}