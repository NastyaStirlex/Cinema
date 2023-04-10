package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.callbacks.GetEpisodeTimeCallback
import com.nastirlex.cinema.data.dto.EpisodeTimeDto

interface EpisodesRepository {
    suspend fun saveEpisodeTime(time: EpisodeTimeDto, episodeId: String)

    fun getEpisodeTime(episodeId: String, callback: GetEpisodeTimeCallback<EpisodeTimeDto>)
}