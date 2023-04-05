package com.nastirlex.cinema.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetCoverCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.utils.Event
import kotlinx.coroutines.launch

class MainViewModel(private var movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {

    private val _mainScreenState = MutableLiveData<Event<Any>>()
    val mainScreenState: MutableLiveData<Event<Any>>
        get() = _mainScreenState

    private var _cover = MutableLiveData<CoverDto>()
    val cover: MutableLiveData<CoverDto>
        get() = _cover

    private val _trends = MutableLiveData<List<MovieDto>>()
    val trends: MutableLiveData<List<MovieDto>>
        get() = _trends

    private val _viewed = MutableLiveData<List<MovieDto>>()
    val viewed: MutableLiveData<List<MovieDto>>
        get() = _viewed

    private val _fresh = MutableLiveData<List<MovieDto>>()
    val fresh: MutableLiveData<List<MovieDto>>
        get() = _fresh

    private val _forYou = MutableLiveData<List<MovieDto>>()
    val forYou: MutableLiveData<List<MovieDto>>
        get() = _forYou

    init {
        getCover()
        getTrends()
        getLastView()
        getFresh()
        getForYou()
    }

    private fun getCover() = viewModelScope.launch {
        movieRepositoryImpl.getCover(
            object : GetCoverCallback<CoverDto> {
                override fun onSuccess(cover: CoverDto) {
                    _cover.value = cover
                }

                override fun onError(error: String?) {}

            }
        )
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

    private fun getLastView() = viewModelScope.launch {
        movieRepositoryImpl.getLastView(
            object : GetMoviesCallback<MovieDto> {
                override fun onSuccess(movies: List<MovieDto>) {
                    _viewed.value = movies
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
                    _fresh.value = movies
                }

                override fun onError(error: String?) {}
            }
        )
    }
}