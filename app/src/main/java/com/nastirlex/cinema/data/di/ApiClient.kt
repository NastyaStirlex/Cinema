package com.nastirlex.cinema.data.di

import android.content.Context
import com.nastirlex.cinema.data.di.service.AuthService
import com.nastirlex.cinema.data.di.service.ChatsService
import com.nastirlex.cinema.data.di.service.EpisodesService
import com.nastirlex.cinema.data.di.service.MovieService
import com.nastirlex.cinema.data.di.service.ProfileService
import com.nastirlex.cinema.data.di.service.RefreshService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://107684.web.hosting-russia.ru:8000/api/"

    private lateinit var movieService: MovieService
    private lateinit var authService: AuthService
    private lateinit var episodesService: EpisodesService
    private lateinit var refreshService: RefreshService
    private lateinit var profileService: ProfileService
    private lateinit var chatsService: ChatsService

    fun getAuthApiService(context: Context): AuthService {
        if (!::authService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getTokenOkHttpClient(context))
                .build()

            authService = retrofit.create(AuthService::class.java)
        }

        return authService
    }

    fun getRefreshService(context: Context): RefreshService {
        if (!::refreshService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getOkHttpClient(context))
                .build()

            refreshService = retrofit.create(RefreshService::class.java)
        }

        return refreshService
    }

    fun getMovieApiService(context: Context): MovieService {
        if (!::movieService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getTokenOkHttpClient(context))
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
                .client(getTokenOkHttpClient(context))
                .build()

            episodesService = retrofit.create(EpisodesService::class.java)
        }

        return episodesService
    }

    fun getProfileApiService(context: Context): ProfileService {
        if (!::profileService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getTokenOkHttpClient(context))
                .build()

            profileService = retrofit.create(ProfileService::class.java)
        }

        return profileService
    }

    fun getChatsService(context: Context): ChatsService {
        if (!::chatsService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getTokenOkHttpClient(context))
                .build()

            chatsService = retrofit.create(ChatsService::class.java)
        }

        return chatsService
    }



}