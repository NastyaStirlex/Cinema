package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.EpisodeTimeDto

interface GetEpisodeTimeCallback<T> {
    fun onSuccess(
        episodeTime: EpisodeTimeDto
    )

    fun onError(error: String?)
}