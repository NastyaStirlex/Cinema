package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.dto.TokenDto
import retrofit2.Response

interface RefreshRepository {
    suspend fun refresh(refreshToken: String): TokenDto
}