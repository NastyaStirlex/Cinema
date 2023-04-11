package com.nastirlex.cinema.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetEpisodesCallback
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import kotlinx.coroutines.launch

class MovieViewModel(
    movieId: String,
    private val movieRepositoryImpl: MovieRepositoryImpl
) : ViewModel() {
    private var _episodes = MutableLiveData<List<EpisodeDto>>()
    val episodes: LiveData<List<EpisodeDto>>
        get() = _episodes

    private var _firstEpisode = MutableLiveData<EpisodeDto>()
    val firstEpisode: LiveData<EpisodeDto>
        get() = _firstEpisode

    init {
        getEpisodes(movieId)
    }

    private fun getEpisodes(movieId: String) = viewModelScope.launch {
        movieRepositoryImpl.getEpisodes(
            movieId = movieId,
            object : GetEpisodesCallback<List<EpisodeDto>> {
                override fun onSuccess(episodes: List<EpisodeDto>) {
                    _episodes.value = episodes
                    _firstEpisode.value = episodes[0]
                }

                override fun onError(error: String?) {}
            }
        )
    }
}