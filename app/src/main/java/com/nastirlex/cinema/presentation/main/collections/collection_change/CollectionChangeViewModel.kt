package com.nastirlex.cinema.presentation.main.collections.collection_change

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.database.Collection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionChangeViewModel(
    private val application: Application,
    private val collectionId: Long
    ) : ViewModel() {

    private val collectionDatabaseRepositoryImpl by lazy {
        CollectionDatabaseRepositoryImpl(
            application
        )
    }

    fun updateCollection(name: String, icon: Int) = viewModelScope.launch(Dispatchers.IO) {
        collectionDatabaseRepositoryImpl.updateCollection(
            Collection(
                name = name,
                icon = icon,
                id = collectionId
            )
        )
    }

    fun deleteCollection(collectionId: Long) = viewModelScope.launch(Dispatchers.IO) {
        collectionDatabaseRepositoryImpl.deleteCollection(collectionId)
    }
}