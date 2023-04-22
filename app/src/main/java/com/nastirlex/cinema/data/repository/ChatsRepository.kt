package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.callbacks.GetChatInfoCallback
import com.nastirlex.cinema.data.callbacks.GetChatsCallback
import com.nastirlex.cinema.data.callbacks.GetMessageCallback
import com.nastirlex.cinema.data.dto.ChatDto
import com.nastirlex.cinema.data.dto.ChatInfoDto
import com.nastirlex.cinema.data.dto.MessageAbbrDto
import com.nastirlex.cinema.data.dto.MessageDto

interface ChatsRepository {
    fun getChats(callback: GetChatsCallback<List<ChatDto>>)

    fun getChatInfo(chatId: String, callback: GetChatInfoCallback<ChatInfoDto>)

    suspend fun sendMessage(
        chatId: String,
        message: MessageAbbrDto,
        callback: GetMessageCallback<MessageDto>
    )
}