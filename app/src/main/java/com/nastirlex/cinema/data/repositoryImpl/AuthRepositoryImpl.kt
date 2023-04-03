package com.nastirlex.cinema.data.repositoryImpl

import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.repository.AuthRepository
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

class AuthRepositoryImpl: AuthRepository {

    //val context = application

    override suspend fun login(loginBody: LoginBodyDto) {
        try {

//            val response = apiService.login(loginBody = loginBody)
//
//            if (response.isSuccessful) {
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
//
//            } else if (response.errorBody() != null) {
//                if (response.code() == 400) {}
//            }


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
}