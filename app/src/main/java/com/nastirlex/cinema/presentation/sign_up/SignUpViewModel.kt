package com.nastirlex.cinema.presentation.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.utils.Event
import kotlinx.coroutines.launch

class SignUpViewModel(private var authRepositoryImpl: AuthRepositoryImpl) : ViewModel() {
    private val _signUpScreenState = MutableLiveData<Event<TokenDto>>()
    val signUpScreenState: MutableLiveData<Event<TokenDto>>
        get() = _signUpScreenState

    fun onClickRegister(registerBody: RegisterBodyDto) = viewModelScope.launch {
        authRepositoryImpl.register(
            registerBody = registerBody,
            _signUpScreenState
        )

    }
}