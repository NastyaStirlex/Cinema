package com.nastirlex.cinema.presentation.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.presentation.main.Event
import kotlinx.coroutines.launch

class SignInViewModel (
    private var authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

    private val _signInScreenState = MutableLiveData<Event<TokenDto>>()
    val signInScreenState: MutableLiveData<Event<TokenDto>>
        get() = _signInScreenState

    fun onClickLogin(email: String, password: String) = viewModelScope.launch {
        authRepositoryImpl.login(
            loginBody = LoginBodyDto(email, password),
            _signInScreenState
        )

    }
}