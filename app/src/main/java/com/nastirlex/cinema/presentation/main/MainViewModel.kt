package com.nastirlex.cinema.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetCoverCallback
import com.nastirlex.cinema.data.callbacks.GetEpisodesCallback
import com.nastirlex.cinema.data.callbacks.GetHistoryCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.EpisodeViewDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private var movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {

    private val _mainScreenState = MutableLiveData<Resource<CoverDto>>()
    val mainScreenState: LiveData<Resource<CoverDto>>
        get() = _mainScreenState

    private var _cover = MutableLiveData<CoverDto>()
    val cover: LiveData<CoverDto>
        get() = _cover

    private val _trends = MutableLiveData<List<MovieDto>>()
    val trends: LiveData<List<MovieDto>>
        get() = _trends

    private val _lastView = MutableLiveData<List<MovieDto>>()
    val lastView: LiveData<List<MovieDto>>
        get() = _lastView

    private val _fresh = MutableLiveData<List<MovieDto>>()
    val fresh: LiveData<List<MovieDto>>
        get() = _fresh

    private val _forYou = MutableLiveData<List<MovieDto>>()
    val forYou: LiveData<List<MovieDto>>
        get() = _forYou

    private val _history = MutableLiveData<List<EpisodeViewDto>>()
    val history: LiveData<List<EpisodeViewDto>>
        get() = _history

    private val _episodes = MutableLiveData<List<EpisodeDto>>()
    val episodes: LiveData<List<EpisodeDto>>
        get() = _episodes

    init {
        //getCover()
        getTrends()
        getLastView()
        getHistory()
        getFresh()
        getForYou()
    }

    fun getCover() = viewModelScope.launch {
        _mainScreenState.value = Resource.Loading()
        movieRepositoryImpl.getCover(
            object : GetCoverCallback<CoverDto> {
                override fun onSuccess(cover: CoverDto) {
                    _cover.value = cover
                }

                override fun onError(error: String?) {}

            }
        )
        _mainScreenState.value = Resource.Success(CoverDto("", ""))
    }

    private fun getTrends() = viewModelScope.launch {
        movieRepositoryImpl.getInTrend(
            object : GetMoviesCallback<MovieDto> {
                override fun onSuccess(trends: List<MovieDto>) {
                    _trends.value = trends
                }

                override fun onError(error: String?) {}
            }
        )
    }

    fun getLastView() = viewModelScope.launch {
        movieRepositoryImpl.getLastView(
            object : GetMoviesCallback<MovieDto> {
                override fun onSuccess(movies: List<MovieDto>) {
                    _lastView.value = movies
                }

                override fun onError(error: String?) {}
            }
        )
    }

    private fun getFresh() = viewModelScope.launch {
        movieRepositoryImpl.getFresh(
            object : GetMoviesCallback<MovieDto> {
                override fun onSuccess(movies: List<MovieDto>) {
                    _fresh.value = movies
                }

                override fun onError(error: String?) {}
            }
        )
    }

    private fun getForYou() = viewModelScope.launch {
        movieRepositoryImpl.getForYou(
            object : GetMoviesCallback<MovieDto> {
                override fun onSuccess(movies: List<MovieDto>) {
                    _forYou.value = movies
                }

                override fun onError(error: String?) {}
            }
        )
    }

    fun getHistory() = viewModelScope.launch {
        movieRepositoryImpl.getHistory(
            object : GetHistoryCallback<List<EpisodeViewDto>> {
                override fun onSuccess(episodesView: List<EpisodeViewDto>) {
                    _history.value = episodesView
//                    if (!_history.value.isNullOrEmpty()) {
//                        getEpisodes(episodesView[0].movieId)
//                    }
                }

                override fun onError(error: String?) {}
            }
        )
    }

    fun getEpisodes(movieId: String) = viewModelScope.launch {
        movieRepositoryImpl.getEpisodes(
            movieId = movieId,
            object : GetEpisodesCallback<List<EpisodeDto>> {
                override fun onSuccess(episodes: List<EpisodeDto>) {
                    _episodes.value = episodes
                }

                override fun onError(error: String?) {}
            }
        )
    }
}