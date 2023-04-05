package com.nastirlex.cinema.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import kotlinx.coroutines.launch

class SignInViewModel (
    private var authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

    fun onClickLogin(loginBody: LoginBodyDto) = viewModelScope.launch {
        authRepositoryImpl.login(
            loginBody = loginBody
        )

    }
}