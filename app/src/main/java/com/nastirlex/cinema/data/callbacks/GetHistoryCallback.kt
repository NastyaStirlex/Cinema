package com.nastirlex.cinema.data.callbacks

import com.nastirlex.cinema.data.dto.EpisodeViewDto

interface GetHistoryCallback<T> {
    fun onSuccess(
        episodesView: List<EpisodeViewDto>
    )

    fun onError(error: String?)
}