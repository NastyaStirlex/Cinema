package com.nastirlex.cinema.data.repositoryImpl

import androidx.lifecycle.MutableLiveData
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repository.AuthRepository
import com.nastirlex.cinema.utils.Event
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

class AuthRepositoryImpl(
    //private val jwtRepositoryImpl: JwtRepositoryImpl,
    //application: Context
) : AuthRepository {

    //val context = application

    override suspend fun login(
        loginBody: LoginBodyDto,
        state: MutableLiveData<Event<TokenDto>>
    ) {
        try {
            state.value = Event.loading()

            val response = ApiClient.authApiService.login(loginBody = loginBody)

            if (response.isSuccessful) {
                state.value = Event.success(response.body())
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
                    state.value = Event.error(R.string.error_incorrect_fields)
                }
            }


        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    //signInScreenState.value = Event.error(R.string.http_error)
                }
                is UnknownHostException, is SocketException -> {
                    //signInScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
            //signInScreenState.value = Event.error(error = null)
        }
    }

    override suspend fun register(
        registerBody: RegisterBodyDto,
        state: MutableLiveData<Event<TokenDto>>
    ) {
        try {
            state.value = Event.loading()

            val response = ApiClient.authApiService.register(registerBody)

            if (response.isSuccessful) {
                state.value = Event.success(response.body())
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
                    state.value = Event.error(R.string.error_repeat_user)
                } else if (response.code() == 422) {
                    state.value = Event.error(R.string.error_invalid_field)
                }
            }
        }
        catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    //signInScreenState.value = Event.error(R.string.http_error)
                }
                is UnknownHostException, is SocketException -> {
                    //signInScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
            //signInScreenState.value = Event.error(error = null)
        }
    }
}