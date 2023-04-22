package com.nastirlex.cinema.presentation.chats

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetChatsCallback
import com.nastirlex.cinema.data.dto.ChatDto
import com.nastirlex.cinema.data.repositoryImpl.ChatsRepositoryImpl
import kotlinx.coroutines.launch

class ChatListViewModel(private val application: Application): ViewModel() {

    private val chatsRepositoryImpl by lazy { ChatsRepositoryImpl(application) }

    private val _chats = MutableLiveData<List<ChatDto>>()
    val chats: LiveData<List<ChatDto>>
        get() = _chats

    init {
        getChats()
    }

    private fun getChats() = viewModelScope.launch {
        chatsRepositoryImpl.getChats(
            object : GetChatsCallback<List<ChatDto>> {
                override fun onSuccess(chats: List<ChatDto>) {
                    _chats.value = chats
                }

                override fun onError(error: String?) {}
            }
        )
    }
}