package com.nastirlex.cinema.data.di.service

import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginBody: LoginBodyDto): Response<TokenDto>

    @POST("auth/register")
    suspend fun register(@Body registerBody: RegisterBodyDto): Response<TokenDto>
}