package com.nastirlex.cinema.presentation.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.presentation.main.Event
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val authRepositoryImpl by lazy { AuthRepositoryImpl() }

    private val _signInScreenState = MutableLiveData<Resource<TokenDto>>(Resource.Default())
    val signInScreenState: MutableLiveData<Resource<TokenDto>>
        get() = _signInScreenState

    fun onClickLogin(email: String, password: String) = viewModelScope.launch {
        authRepositoryImpl.login(
            LoginBodyDto(
                email = email,
                password = password
            ),
            _signInScreenState
        )

    }
}