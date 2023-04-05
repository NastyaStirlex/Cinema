package com.nastirlex.cinema.data.repository

import androidx.lifecycle.MutableLiveData
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.utils.Event

interface AuthRepository {
    suspend fun login(loginBody: LoginBodyDto, state: MutableLiveData<Event<TokenDto>>)

    suspend fun register(registerBody: RegisterBodyDto, state: MutableLiveData<Event<TokenDto>>)
}