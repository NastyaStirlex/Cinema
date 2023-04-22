package com.nastirlex.cinema.data.repositoryImpl

import android.app.Application
import android.util.Log
import com.nastirlex.cinema.data.callbacks.GetChatInfoCallback
import com.nastirlex.cinema.data.callbacks.GetChatsCallback
import com.nastirlex.cinema.data.callbacks.GetMessageCallback
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.ChatDto
import com.nastirlex.cinema.data.dto.ChatInfoDto
import com.nastirlex.cinema.data.dto.MessageAbbrDto
import com.nastirlex.cinema.data.dto.MessageDto
import com.nastirlex.cinema.data.repository.ChatsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ChatsRepositoryImpl(private val application: Application) : ChatsRepository {
    private var callGetChats: Call<List<ChatDto>>? = null
    override fun getChats(callback: GetChatsCallback<List<ChatDto>>) {
        callGetChats = ApiClient.getChatsService(application.applicationContext).getChats()
        callGetChats?.enqueue(
            object : Callback<List<ChatDto>> {
                override fun onResponse(
                    call: Call<List<ChatDto>>,
                    response: Response<List<ChatDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                chats = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<List<ChatDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    private var callGetChatInfo: Call<ChatInfoDto>? = null
    override fun getChatInfo(chatId: String, callback: GetChatInfoCallback<ChatInfoDto>) {
        callGetChatInfo = ApiClient.getChatsService(application.applicationContext).getChatInfo(
            chatId = chatId
        )
        callGetChatInfo?.enqueue(
            object : Callback<ChatInfoDto> {
                override fun onResponse(call: Call<ChatInfoDto>, response: Response<ChatInfoDto>) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let {
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                chatInfo = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<ChatInfoDto>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    private var callGetMessage: Call<MessageDto>? = null
    override suspend fun sendMessage(
        chatId: String,
        message: MessageAbbrDto,
        callback: GetMessageCallback<MessageDto>
    ) {
        callGetMessage = ApiClient.getChatsService(application.applicationContext).sendMessage(chatId, message)
        callGetMessage?.enqueue(
            object : Callback<MessageDto> {
                override fun onResponse(call: Call<MessageDto>, response: Response<MessageDto>) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                message = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<MessageDto>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }
}