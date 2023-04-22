package com.nastirlex.cinema.data.di.interceptors

import android.content.Context
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.RefreshRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RefreshTokenAuthenticator(private val context: Context) : Authenticator {
    private val refreshRepositoryImpl by lazy { RefreshRepositoryImpl(context) }
    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }

    override fun authenticate(route: Route?, response: Response): Request? {
        try {
            val newToken =
                runBlocking {
                    refreshRepositoryImpl.refresh(jwtRepositoryImpl.getRefreshToken(context)!!)
                }

            jwtRepositoryImpl.saveAccessToken(context, newToken.accessToken)
            jwtRepositoryImpl.saveRefreshToken(context, newToken.refreshToken)

        } catch (e: Exception) {
            return response.request.newBuilder()
                .header("Authorization", "Bearer ${jwtRepositoryImpl.getAccessToken(context)}")
                .build()
        }

        return if (response.responseCount >= 2) {
            null
        } else {
            response.request.newBuilder()
                .header("Authorization", "Bearer ${jwtRepositoryImpl.getAccessToken(context)}")
                .build()
        }

    }
}

private val Response.responseCount: Int
    get() = generateSequence(this) { it.priorResponse }.count()