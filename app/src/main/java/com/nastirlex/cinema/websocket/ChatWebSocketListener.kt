package com.nastirlex.cinema.websocket

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.nastirlex.cinema.data.dto.MessageDto
import com.nastirlex.cinema.presentation.chats.chat.ChatViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatWebSocketListener(private val chatViewModel: ChatViewModel) : WebSocketListener() {

//    private val _messages = MutableLiveData<MessageDto>()
//    val messages: LiveData<MessageDto>
//        get() = _messages

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        chatViewModel.setStatus(true)
        Log.d("Socket is going to open!", response.message)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        val message = Gson().fromJson(text, MessageDto::class.java)

        chatViewModel.addMessage(message)

        Log.d("Message received!", message.text)

        //_messages.value = message

//        Handler(Looper.getMainLooper()).post {
//
//        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        webSocket.close(code, reason)
        Log.d("Socket is going to close!", "$code $reason")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        chatViewModel.setStatus(false)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        t.printStackTrace()
        super.onFailure(webSocket, t, response)
    }
}