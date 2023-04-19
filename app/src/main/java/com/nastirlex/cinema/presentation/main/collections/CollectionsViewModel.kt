package com.nastirlex.cinema.presentation.main.collections

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import com.nastirlex.cinema.database.Collection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionsViewModel(application: Application) : ViewModel() {

    private val collectionDatabaseRepositoryImpl by lazy { CollectionDatabaseRepositoryImpl(application) }

    private val _collections = MutableLiveData<List<Collection>>()
    val collections: LiveData<List<Collection>>
        get() = _collections

    init {
        //viewModelScope.launch(Dispatchers.IO) { collectionDatabaseRepositoryImpl.cleanTable() }
        getCollections()
    }


    fun getCollections() = viewModelScope.launch(Dispatchers.IO) {

        _collections.postValue(collectionDatabaseRepositoryImpl.getCollections())

    }
}