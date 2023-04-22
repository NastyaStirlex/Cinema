package com.nastirlex.cinema.presentation.sign_up

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
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.ProfileRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.database.entity.Collection
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.presentation.main.Event
import com.nastirlex.cinema.presentation.main.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel(private val application: Application) : ViewModel() {

    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }
    private val authRepositoryImpl by lazy { AuthRepositoryImpl(application) }
    private val profileRepositoryImpl by lazy { ProfileRepositoryImpl(application) }

    private val collectionDatabaseRepositoryImpl by lazy {
        CollectionDatabaseRepositoryImpl(
            application
        )
    }

    private val _signUpScreenState = MutableLiveData<Resource<TokenDto>>()
    val signUpScreenState: MutableLiveData<Resource<TokenDto>>
        get() = _signUpScreenState

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

    fun onClickRegister(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        repeatPassword: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (email.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank() || repeatPassword.isBlank()) {
            _signUpScreenState.postValue(Resource.Error(R.string.validation_error_empty_fields))
        } else if (!email.matches(Regex("^(([^-A-Z_<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-z0-9]+\\.)+[a-z]{2,}))\$"))) {
            _signUpScreenState.postValue(Resource.Error(R.string.validation_error_invalid_email))
        } else if (password != repeatPassword) {
            _signUpScreenState.postValue(Resource.Error(R.string.validation_error_mismatched_passwords))
        } else {
            authRepositoryImpl.register(
                registerBody = RegisterBodyDto(email, password, firstName, lastName),
                _signUpScreenState
            )

            if (_signUpScreenState.value is Resource.Success) {
                jwtRepositoryImpl.saveAccessToken(
                    application.applicationContext,
                    (_signUpScreenState.value as Resource.Success<TokenDto>).data!!.accessToken
                )

                jwtRepositoryImpl.saveRefreshToken(
                    application.applicationContext,
                    (_signUpScreenState.value as Resource.Success<TokenDto>).data!!.refreshToken
                )

                collectionDatabaseRepositoryImpl.insertCollection(
                    Collection(
                        name = "Favourites",
                        icon = R.drawable.ic_heart
                    )
                )

                getProfile()
                jwtRepositoryImpl.saveUserId(application.applicationContext, _userId.value!!)
            }



        }
    }

    private fun getProfile() = viewModelScope.launch {
        profileRepositoryImpl.getProfile(
            object : GetProfileCallback<ProfileDto> {
                override fun onSuccess(profile: ProfileDto) {
                    _userId.value = profile.userId
                }

                override fun onError(error: String?) {}
            }
        )
    }
}