package com.nastirlex.cinema.data.di.service

import com.nastirlex.cinema.data.dto.ChatDto
import com.nastirlex.cinema.data.dto.ChatInfoDto
import com.nastirlex.cinema.data.dto.MessageAbbrDto
import com.nastirlex.cinema.data.dto.MessageDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatsService {
    @GET("chats")
    fun getChats(): Call<List<ChatDto>>

    @GET("chats/{chatId}")
    fun getChatInfo(@Path("chatId") chatId: String): Call<ChatInfoDto>

    @POST("chats/{chatId}/messages")
    suspend fun sendMessage(
        @Path("chatId") chatId: String,
        @Body message: MessageAbbrDto
    ): Call<MessageDto>
}