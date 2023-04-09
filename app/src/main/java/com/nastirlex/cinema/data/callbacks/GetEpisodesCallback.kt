package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.EpisodeDto

interface GetEpisodesCallback<T> {
    fun onSuccess(
        episodes: List<EpisodeDto>
    )

    fun onError(error: String?)
}