package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.dto.LoginBodyDto

interface AuthRepository {
    suspend fun login(loginBody: LoginBodyDto)
}