package com.nastirlex.cinema.data.di.interceptors

import android.content.Context
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.RefreshRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.lang.Exception

class RefreshTokenAuthenticator(private val context: Context) : Authenticator {

    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }
    private val refreshRepositoryImpl by lazy { RefreshRepositoryImpl(context) }

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = jwtRepositoryImpl.getAccessToken(context)
        val refreshToken = jwtRepositoryImpl.getRefreshToken(context)

        try {
            runBlocking {
                val response = refreshToken?.let { refreshRepositoryImpl.refresh(it) }
                jwtRepositoryImpl.saveAccessToken(context, response?.accessToken!!)
                jwtRepositoryImpl.saveRefreshToken(context, response.refreshToken)
            }

        } catch (e: Exception) {
            return response.request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        }

        return if (response.responseCount >= 2) {
            null
        } else {
            response.request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        }

    }
}

private val Response.responseCount: Int
    get() = generateSequence(this) { it.priorResponse }.count()