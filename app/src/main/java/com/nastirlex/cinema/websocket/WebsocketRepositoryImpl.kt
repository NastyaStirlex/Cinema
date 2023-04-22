package com.nastirlex.cinema.websocket

import android.app.Application
import android.util.Log
import com.nastirlex.cinema.data.di.interceptors.AuthorizationInterceptor
import com.nastirlex.cinema.data.di.interceptors.RefreshTokenAuthenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.lang.Exception
import java.util.concurrent.TimeUnit

class WebsocketRepositoryImpl(
    private val application: Application,
    private val chatWebSocketListener: ChatWebSocketListener,
    private val chatId: String
) {
    private lateinit var websocket: WebSocket


    fun websocket() {
        try {
            val client = OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .authenticator(RefreshTokenAuthenticator(application.applicationContext))
                .addInterceptor(AuthorizationInterceptor(application.applicationContext))
                .build()
            val request = Request.Builder()
                .url("ws://107684.web.hosting-russia.ru:8000/api/chats/$chatId/messages")
                .build()

            websocket = client.newWebSocket(request, chatWebSocketListener)

            client.dispatcher.executorService.shutdown()
        } catch (e: Exception) {
            Log.d("get websocket error", e.message ?: "null")
        }
    }
}