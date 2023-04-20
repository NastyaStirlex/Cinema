package com.nastirlex.cinema.data.repositoryImpl

import android.app.Application
import android.util.Log
import com.nastirlex.cinema.data.callbacks.GetEpisodeTimeCallback
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.EpisodeTimeDto
import com.nastirlex.cinema.data.repository.EpisodesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

class EpisodesRepositoryImpl(private val application: Application) : EpisodesRepository {

    override suspend fun saveEpisodeTime(time: EpisodeTimeDto, episodeId: String) {
        try {
            ApiClient.getEpisodesApiService(application.applicationContext).saveEpisodeTime(
                episodeId = episodeId,
                time = time
            )
        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    //profileScreenState.value = Event.error(R.string.http_error)
                }

                is UnknownHostException, is SocketException -> {
                    //profileScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
        }
    }

    private var callGetEpisodeTime: Call<EpisodeTimeDto>? = null
    override fun getEpisodeTime(
        episodeId: String,
        callback: GetEpisodeTimeCallback<EpisodeTimeDto>
    ) {
        callGetEpisodeTime = ApiClient.getEpisodesApiService(application.applicationContext).getEpisodeTime(
            episodeId = episodeId
        )
        callGetEpisodeTime?.enqueue(
            object : Callback<EpisodeTimeDto> {
                override fun onResponse(
                    call: Call<EpisodeTimeDto>,
                    response: Response<EpisodeTimeDto>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                episodeTime = it
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

                override fun onFailure(call: Call<EpisodeTimeDto>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }
}