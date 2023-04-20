package com.nastirlex.cinema.presentation.episode

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetEpisodeTimeCallback
import com.nastirlex.cinema.data.callbacks.GetEpisodesCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.EpisodeTimeDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.EpisodesRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.database.entity.Collection
import com.nastirlex.cinema.database.entity.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeViewModel(
    application: Application,
    private val movieId: String,
    private val episodeId: String
) : ViewModel() {

    private val movieRepositoryImpl by lazy { MovieRepositoryImpl(application) }
    private val episodesRepositoryImpl by lazy { EpisodesRepositoryImpl(application) }

    private val collectionDatabaseRepositoryImpl by lazy {
        CollectionDatabaseRepositoryImpl(
            application
        )
    }

    private var favouritesId: Long = 0

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

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

    private var _episodeTime = MutableLiveData<Int>()
    val episodeTime: LiveData<Int>
        get() = _episodeTime


    private val _viewed = MutableLiveData<List<MovieDto>>()
    val viewed: LiveData<List<MovieDto>>
        get() = _viewed

    private val _collections = MutableLiveData<List<Collection>>()
    val collections: LiveData<List<Collection>>
        get() = _collections


    init {
        getFavouritesId()
        isFavouriteFilm(movieId)
        getEpisodeTime()
        getEpisodeDetails(movieId, episodeId)
        getLastView()
        getCollections()

    }

    private fun getEpisodeDetails(movieId: String, episodeId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            movieRepositoryImpl.getEpisodes(
                movieId = movieId,
                object : GetEpisodesCallback<List<EpisodeDto>> {
                    override fun onSuccess(episodes: List<EpisodeDto>) {
                        val episode = episodes.find { it.episodeId == episodeId }
                        if (episode != null) {
                            _name.value = episode.name
                            _description.value = episode.description
                            _filepath.value = episode.filePath
                            _runtime.value = episode.runtime
                            _years.value =
                                episodes.minOfOrNull { it.year } + " - " + episodes.maxOfOrNull { it.year }
                        }
                    }

                    override fun onError(error: String?) {}
                }
            )
        }

    private fun getLastView() = viewModelScope.launch(Dispatchers.IO) {
        movieRepositoryImpl.getLastView(
            object : GetMoviesCallback<MovieDto> {
                override fun onSuccess(movies: List<MovieDto>) {
                    _viewed.value = movies
                }

                override fun onError(error: String?) {}
            }
        )
    }

    private fun getCollections() = viewModelScope.launch(Dispatchers.IO) {
        _collections.postValue(collectionDatabaseRepositoryImpl.getCollections())
    }

    fun saveEpisodeTime(time: Int?) = viewModelScope.launch(Dispatchers.IO) {
        if (time != null) {
            episodesRepositoryImpl.saveEpisodeTime(
                time = EpisodeTimeDto(time),
                episodeId = episodeId
            )
        }
    }

    private fun getEpisodeTime() = viewModelScope.launch(Dispatchers.IO) {
        episodesRepositoryImpl.getEpisodeTime(
            episodeId = episodeId,
            object : GetEpisodeTimeCallback<EpisodeTimeDto> {
                override fun onSuccess(episodeTime: EpisodeTimeDto) {
                    _episodeTime.value = episodeTime.time
                }

                override fun onError(error: String?) {}
            }
        )
    }

    fun addFilmToCollection(
        poster: String,
        name: String,
        description: String,
        collectionId: Long,
        id: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        collectionDatabaseRepositoryImpl.insertCollectionFilm(
            Film(
                poster = poster,
                name = name,
                description = description,
                collectionId = collectionId,
                id = id
            )
        )
    }

    fun getFavouritesId() = viewModelScope.launch(Dispatchers.IO) {
        favouritesId = collectionDatabaseRepositoryImpl.getFavouritesId()

        Log.d("favouritesId", collectionDatabaseRepositoryImpl.getFavouritesId().toString())
    }

    fun addFilmToFavourites(
        poster: String,
        name: String,
        description: String,
        id: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            Film(
                poster = poster,
                name = name,
                description = description,
                collectionId = favouritesId,
                id = id
            ).let {
                collectionDatabaseRepositoryImpl.insertCollectionFilm(
                    it
                )
            }
        } catch (e: Exception) {
            when (e) {
                is SQLiteConstraintException -> {}
            }
        }
    }

    fun deleteFilmFromFavourites() = viewModelScope.launch(Dispatchers.IO) {
        collectionDatabaseRepositoryImpl.deleteFilm(
            movieId = movieId,
            collectionId = favouritesId
        )
    }

    fun isFavouriteFilm(movieId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _isFavourite.postValue(
                if (
                collectionDatabaseRepositoryImpl.isFilmInCollection(
                    movieId = movieId,
                    collectionId = favouritesId
                ) != null) true else false
            )
            Log.d("my isfavourite", _isFavourite.value.toString())

            Log.d(
                "isfavourite", collectionDatabaseRepositoryImpl.isFilmInCollection(
                    movieId = movieId,
                    collectionId = favouritesId
                ) ?: ""
            )
            Log.d("movieId ", movieId)
            Log.d("colletionId", favouritesId.toString())
        }

    fun deleteFilmsTable() = viewModelScope.launch(Dispatchers.IO) {
        collectionDatabaseRepositoryImpl.cleatFilmsTable()
    }
}