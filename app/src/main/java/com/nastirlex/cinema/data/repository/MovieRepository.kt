package com.nastirlex.cinema.data.repository

import com.nastirlex.cinema.data.callbacks.GetCoverCallback
import com.nastirlex.cinema.data.callbacks.GetEpisodesCallback
import com.nastirlex.cinema.data.callbacks.GetHistoryCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.EpisodeViewDto
import com.nastirlex.cinema.data.dto.MovieDto

interface MovieRepository {
    fun getCover(callback: GetCoverCallback<CoverDto>)

    fun getInTrend(callback: GetMoviesCallback<MovieDto>)

    fun getLastView(callback: GetMoviesCallback<MovieDto>)

    fun getFresh(callback: GetMoviesCallback<MovieDto>)

    fun getForYou(callback: GetMoviesCallback<MovieDto>)

    fun getHistory(callback: GetHistoryCallback<List<EpisodeViewDto>>)

    fun getCompilation(callback: GetMoviesCallback<MovieDto>)

    fun getEpisodes(movieId: String, callback: GetEpisodesCallback<List<EpisodeDto>>)

    suspend fun deleteFilmFromCompilation(movieId: String)
}