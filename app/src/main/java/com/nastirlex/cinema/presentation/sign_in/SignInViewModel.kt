package com.nastirlex.cinema.presentation.sign_in

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.callbacks.GetProfileCallback
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.ProfileDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.ProfileRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.presentation.main.Event
import kotlinx.coroutines.launch

class SignInViewModel(private val application: Application) : ViewModel() {

    private val authRepositoryImpl by lazy { AuthRepositoryImpl(application) }
    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }
    private val profileRepositoryImpl by lazy { ProfileRepositoryImpl(application) }

    private val _signInScreenState = MutableLiveData<Resource<TokenDto>>(Resource.Default())
    val signInScreenState: MutableLiveData<Resource<TokenDto>>
        get() = _signInScreenState

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

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

            //if (_signInScreenState.value is Resource.Success) {
                jwtRepositoryImpl.saveAccessToken(
                    application.applicationContext,
                    (_signInScreenState.value as Resource.Success<TokenDto>).data!!.accessToken
                )

                Log.d("access token", jwtRepositoryImpl.getAccessToken(application.applicationContext)!!)

                jwtRepositoryImpl.saveRefreshToken(
                    application.applicationContext,
                    (_signInScreenState.value as Resource.Success<TokenDto>).data!!.refreshToken
                )

                getProfile()


            //_signInScreenState.value = Resource.Success()

            //}
        }
    }

    private fun getProfile() = viewModelScope.launch {
        profileRepositoryImpl.getProfile(
            object : GetProfileCallback<ProfileDto> {
                override fun onSuccess(profile: ProfileDto) {
                    _userId.value = profile.userId
                    jwtRepositoryImpl.saveUserId(application.applicationContext, _userId.value!!)
                    Log.d("profile id", profile.userId)
                }

                override fun onError(error: String?) {}
            }
        )
    }
}