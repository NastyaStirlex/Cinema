package com.nastirlex.cinema.data.repository

import androidx.lifecycle.MutableLiveData
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.presentation.main.Event

interface AuthRepository {
    suspend fun login(loginBody: LoginBodyDto, state: MutableLiveData<Resource<TokenDto>>)

    suspend fun register(registerBody: RegisterBodyDto, state: MutableLiveData<Resource<TokenDto>>)
}