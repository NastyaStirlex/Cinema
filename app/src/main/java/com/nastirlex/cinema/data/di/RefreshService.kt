package com.nastirlex.cinema.data.di

import com.nastirlex.cinema.data.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface RefreshService {
    @POST("auth/refresh")
    suspend fun refresh(@Header("Authorization") refreshToken: String): TokenDto
}