package com.nastirlex.cinema.data.repositoryImpl

import android.content.Context
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repository.RefreshRepository
import retrofit2.Call
import retrofit2.Response

class RefreshRepositoryImpl(private val context: Context): RefreshRepository {

    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }

     override suspend fun refresh(refreshToken: String): TokenDto {
        return ApiClient.getRefreshService(context).refresh(
            refreshToken = "Bearer ${jwtRepositoryImpl.getRefreshToken(context)}"
        )
    }

}