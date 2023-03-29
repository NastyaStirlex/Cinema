package com.nastirlex.cinema.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private var authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

    fun onClickLogin(loginBody: LoginBodyDto) = viewModelScope.launch {
        authRepositoryImpl.login(
            loginBody = loginBody
        )

    }
}