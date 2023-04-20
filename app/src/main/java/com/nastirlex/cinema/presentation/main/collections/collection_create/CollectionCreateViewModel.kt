package com.nastirlex.cinema.presentation.main.collections.collection_create

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.database.entity.Collection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CollectionCreateViewModel(
    private val application: Application
) : ViewModel() {

    private val collectionDatabaseRepositoryImpl by lazy { CollectionDatabaseRepositoryImpl(application) }

    private val _collectionCreateScreenState = MutableLiveData<Resource<Any>>(Resource.Default())
    val collectionCreateScreenState: LiveData<Resource<Any>>
        get() = _collectionCreateScreenState

    fun createCollection(name: String, icon: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _collectionCreateScreenState.postValue(Resource.Loading())
            collectionDatabaseRepositoryImpl.insertCollection(
                Collection(
                    name = name,
                    icon = icon
                )
            )
            _collectionCreateScreenState.postValue(Resource.Success(null))
        } catch (e: Exception) {
            when (e) {
                is SQLiteConstraintException -> {
                    _collectionCreateScreenState.postValue(Resource.Error(R.string.error_nonunique_fields))
                }
            }
        }

    }
}