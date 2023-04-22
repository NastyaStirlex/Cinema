package com.nastirlex.cinema.presentation.main.compilation

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.database.entity.Film
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompilationViewModel(
    private val application: Application,
) : ViewModel() {

    private val movieRepositoryImpl by lazy { MovieRepositoryImpl(application) }

    private val collectionDatabaseRepositoryImpl by lazy { CollectionDatabaseRepositoryImpl(application) }

    private var favouritesId: Long = 0

    private val _compilationScreenState = MutableLiveData<Resource<Any>>(Resource.Default())
    val compilationScreenState: MutableLiveData<Resource<Any>>
        get() = _compilationScreenState

    private val _compilation = MutableLiveData<List<MovieDto>>()
    val compilation: LiveData<List<MovieDto>>
        get() = _compilation


    init {
        getFavouritesId()
    }

    fun getCompilation() = viewModelScope.launch {
        movieRepositoryImpl.getCompilation(
            object : GetMoviesCallback<MovieDto> {
                override fun onSuccess(movies: List<MovieDto>) {
                    _compilation.value = movies
                }

                override fun onError(error: String?) {}
            }
        )
    }

    fun deleteFilmFromCompilation(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        movieRepositoryImpl.deleteFilmFromCompilation(movieId = movieId)
    }

    fun deleteFilmFromFavourites(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        //try {
            collectionDatabaseRepositoryImpl.deleteFilm(
                movieId = movieId,
                collectionId = favouritesId
            )
//        } catch (e: java.lang.Exception) {
//            when (e) {
//                is SQLiteConstraintException -> {
//                    Log.d("error while deleting from fav", e.message ?: "null")
//                    _compilationScreenState.value = Resource.Error(R.string.error_film_is_not_in_favourite)
//                }
//            }
//        }
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
                is SQLiteConstraintException -> {
                    _compilationScreenState.value = Resource.Error(R.string.error_film_in_collectione_yet)
                }
            }
        }
    }

    private fun getFavouritesId() = viewModelScope.launch(Dispatchers.IO) {
        favouritesId = collectionDatabaseRepositoryImpl.getFavouritesId()
    }

}