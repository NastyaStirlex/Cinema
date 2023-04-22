package com.nastirlex.cinema.presentation.chats.chat

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.callbacks.GetChatInfoCallback
import com.nastirlex.cinema.data.callbacks.GetMessageCallback
import com.nastirlex.cinema.data.dto.ChatInfoDto
import com.nastirlex.cinema.data.dto.MessageAbbrDto
import com.nastirlex.cinema.data.dto.MessageDto
import com.nastirlex.cinema.data.repositoryImpl.ChatsRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.websocket.ChatWebSocketListener
import com.nastirlex.cinema.websocket.WebsocketRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(
    private val application: Application,
    private val chatId: String
) :
    ViewModel() {

    private val _chatScreenState = MutableLiveData<Resource<Any>>(Resource.Default())
    val chatScreenState: LiveData<Resource<Any>>
        get() = _chatScreenState

    private val _socketStatus = MutableLiveData(false)
    val socketStatus: LiveData<Boolean>
        get() = _socketStatus

    private val _messages = MutableLiveData<MessageDto>()
    val messages: LiveData<MessageDto>
        get() = _messages

    private val _sendedMessage = MutableLiveData<MessageDto>()
    val sendedMessage: LiveData<MessageDto>
        get() = _sendedMessage

    private val chatsRepositoryImpl by lazy { ChatsRepositoryImpl(application) }

    private val _chatName = MutableLiveData<String>()
    val chatName: LiveData<String>
        get() = _chatName

    init {
        getChatInfo()
    }

    private fun getChatInfo() = viewModelScope.launch {
        chatsRepositoryImpl.getChatInfo(
            chatId, object : GetChatInfoCallback<ChatInfoDto> {
                override fun onSuccess(chatInfo: ChatInfoDto) {
                    _chatName.value = chatInfo.chatName
                }

                override fun onError(error: String?) {}
            }
        )
    }

    fun sendMessage(message: String) = viewModelScope.launch(Dispatchers.Main) {
        if (message.isEmpty()) {
            _chatScreenState.value = Resource.Error(R.string.error_empty_message)
        } else {
            chatsRepositoryImpl.sendMessage(
                chatId,
                MessageAbbrDto(message),
                object : GetMessageCallback<MessageDto> {
                    override fun onSuccess(message: MessageDto) {
                        _sendedMessage.value = message
                    }

                    override fun onError(error: String?) {}
                }
            )
        }
    }

    fun addMessage(message: MessageDto) = viewModelScope.launch(Dispatchers.Main) {
        if (_socketStatus.value == true) {
            _messages.value = message
        }
    }

    fun setStatus(status: Boolean) = viewModelScope.launch(Dispatchers.Main) {
        _socketStatus.value = status
    }


}