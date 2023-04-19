package com.nastirlex.cinema.presentation.main.compilation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompilationViewModel(
    private val movieRepositoryImpl: MovieRepositoryImpl
) : ViewModel() {

    private val _compilation = MutableLiveData<List<MovieDto>>()
    val compilation: LiveData<List<MovieDto>>
        get() = _compilation

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

}