package com.nastirlex.cinema.presentation.main.collections.collection_info

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.database.entity.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionInfoViewModel(application: Application, private val collectionId: Long) :
    ViewModel() {
    private val collectionDatabaseRepositoryImpl by lazy {
        CollectionDatabaseRepositoryImpl(
            application
        )
    }

    private val _collectionFilms = MutableLiveData<List<Film>>()
    val collectionFilms: LiveData<List<Film>>
        get() = _collectionFilms


    init {
        getCollectionFilms()
    }

    private fun getCollectionFilms() = viewModelScope.launch(Dispatchers.IO) {
        _collectionFilms.postValue(collectionDatabaseRepositoryImpl.getCollectionFilms(collectionId))
    }
}