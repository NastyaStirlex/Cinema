package com.nastirlex.cinema.data.repositoryImpl

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repository.AuthRepository
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.presentation.main.Event
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

class AuthRepositoryImpl(private val application: Application) : AuthRepository {

    //val context = application

    override suspend fun login(
        loginBody: LoginBodyDto,
        state: MutableLiveData<Resource<TokenDto>>
    ) {
        try {
            state.value = Resource.Loading()

            val response = ApiClient.getAuthApiService(application.applicationContext)
                .login(loginBody = loginBody)

            if (response.isSuccessful) {
                state.value = Resource.Success(response.body())
//                response.body()?.accessToken?.let {
//                    jwtRepositoryImpl.saveAccessToken(
//                        context = context,
//                        accessToken = it
//                    )
//                }
//                response.body()?.refreshToken?.let {
//                    jwtRepositoryImpl.saveRefreshToken(
//                        context = context,
//                        refreshToken = it
//                    )
//                }

            } else if (response.errorBody() != null) {
                if (response.code() == 401) {
                    state.value = Resource.Error(R.string.error_incorrect_fields)
                }
            }

        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(Resource.Error(R.string.error_http))
                }

                is UnknownHostException, is SocketException -> {
                    state.postValue(Resource.Error(R.string.error_network))
                }

                else -> {
                    state.postValue(Resource.Error(R.string.error_unknown))
                }
            }
        }
    }

    override suspend fun register(
        registerBody: RegisterBodyDto,
        state: MutableLiveData<Resource<TokenDto>>
    ) {
        try {
            state.postValue(Resource.Loading())

            val response =
                ApiClient.getAuthApiService(application.applicationContext).register(registerBody)

            if (response.isSuccessful) {
                state.postValue(Resource.Success(response.body()))
//                response.body()?.accessToken?.let {
//                    jwtRepositoryImpl.saveAccessToken(
//                        context = context,
//                        accessToken = it
//                    )
//                }
//                response.body()?.refreshToken?.let {
//                    jwtRepositoryImpl.saveRefreshToken(
//                        context = context,
//                        refreshToken = it
//                    )
//                }

            } else if (response.errorBody() != null) {
                if (response.code() == 409) {
                    state.postValue(Resource.Error(R.string.error_repeat_user))
                } else if (response.code() == 422) {
                    state.postValue(Resource.Error(R.string.error_invalid_field))
                }
            }
        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    state.postValue(Resource.Error(R.string.error_http))
                }

                is UnknownHostException, is SocketException -> {
                    state.postValue(Resource.Error(R.string.error_network))
                }

                else -> {
                    state.postValue(Resource.Error(R.string.error_unknown))
                }
            }
        }
    }
}