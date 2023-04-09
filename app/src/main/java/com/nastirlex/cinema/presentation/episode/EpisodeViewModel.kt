package com.nastirlex.cinema.presentation.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetCollectionsCallback
import com.nastirlex.cinema.data.callbacks.GetEpisodesCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import kotlinx.coroutines.launch

class EpisodeViewModel(
    movieId: String,
    episodeId: String,
    private var movieRepositoryImpl: MovieRepositoryImpl,
    private val collectionsRepositoryImpl: CollectionsRepositoryImpl
) : ViewModel() {

    private var _episodes = MutableLiveData<List<EpisodeDto>>()
    val episodes: LiveData<List<EpisodeDto>>
        get() = _episodes

    private var _years = MutableLiveData<String>()
    val years: LiveData<String>
        get() = _years

    private var _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private var _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private var _filepath = MutableLiveData<String>()
    val filepath: LiveData<String>
        get() = _filepath

    private var _runtime = MutableLiveData<Int>()
    val runtime: LiveData<Int>
        get() = _runtime


    private val _viewed = MutableLiveData<List<MovieDto>>()
    val viewed: LiveData<List<MovieDto>>
        get() = _viewed

    private val _collections = MutableLiveData<List<CollectionDto>>()
    val collections: LiveData<List<CollectionDto>>
        get() = _collections


    init {
        getEpisodeDetails(movieId, episodeId)
        getLastView()
        getCollections()
    }

    private fun getEpisodeDetails(movieId: String, episodeId: String) = viewModelScope.launch {
        movieRepositoryImpl.getEpisodes(
            movieId = movieId,
            object : GetEpisodesCallback<List<EpisodeDto>> {
                override fun onSuccess(episodes: List<EpisodeDto>) {
                    _episodes.value = episodes
                    val episode = episodes.find { it.episodeId == episodeId }
                    if (episode != null) {
                        _name.value = episode.name
                        _description.value = episode.description
                        _filepath.value = episode.filePath
                        _runtime.value = episode.runtime
                        _years.value = episodes[0].year + " - " + episodes.last().year
                    }
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

    private fun getCollections() = viewModelScope.launch {
        collectionsRepositoryImpl.getCollections(
            object : GetCollectionsCallback<List<CollectionDto>> {
                override fun onSuccess(collections: List<CollectionDto>) {
                    _collections.value = collections
                }

                override fun onError(error: String?) {}
            }
        )
    }
}