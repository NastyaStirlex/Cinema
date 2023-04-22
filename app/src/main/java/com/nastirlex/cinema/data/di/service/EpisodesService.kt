package com.nastirlex.cinema.data.di.service

import com.nastirlex.cinema.data.dto.EpisodeTimeDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface EpisodesService {
    @POST("episodes/{episodeId}/time")
    suspend fun saveEpisodeTime(
        @Path("episodeId") episodeId: String,
        @Body time: EpisodeTimeDto
    ): Response<Void>

    @GET("episodes/{episodeId}/time")
    fun getEpisodeTime(
        @Path("episodeId") episodeId: String
    ): Call<EpisodeTimeDto>
}