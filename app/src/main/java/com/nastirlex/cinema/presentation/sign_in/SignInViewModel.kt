package com.nastirlex.cinema.presentation.sign_in

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.presentation.main.Event
import kotlinx.coroutines.launch

class SignInViewModel(private val application: Application) : ViewModel() {

    private val authRepositoryImpl by lazy { AuthRepositoryImpl(application) }
    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }

    private val _signInScreenState = MutableLiveData<Resource<TokenDto>>(Resource.Default())
    val signInScreenState: MutableLiveData<Resource<TokenDto>>
        get() = _signInScreenState

    fun onClickLogin(email: String, password: String) = viewModelScope.launch {
        if (email.isBlank() || password.isBlank()) {
            _signInScreenState.value = Resource.Error(R.string.validation_error_empty_fields)
        } else if (!email.matches(Regex("^(([^-A-Z_<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-z0-9]+\\.)+[a-z]{2,}))\$"))) {
            _signInScreenState.value = Resource.Error(R.string.validation_error_invalid_email)
        } else {
            authRepositoryImpl.login(
                LoginBodyDto(
                    email = email,
                    password = password
                ),
                _signInScreenState
            )

            if (_signInScreenState.value is Resource.Success) {
                jwtRepositoryImpl.saveAccessToken(
                    application.applicationContext,
                    (_signInScreenState.value as Resource.Success<TokenDto>).data!!.accessToken
                )

                Log.d(
                    "access token",
                    jwtRepositoryImpl.getAccessToken(application.applicationContext).toString()
                )

                jwtRepositoryImpl.saveRefreshToken(
                    application.applicationContext,
                    (_signInScreenState.value as Resource.Success<TokenDto>).data!!.refreshToken
                )

                Log.d(
                    "refresh token",
                    jwtRepositoryImpl.getRefreshToken(application.applicationContext).toString()
                )

            }
        }
    }
}