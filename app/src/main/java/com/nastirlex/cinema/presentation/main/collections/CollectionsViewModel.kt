package com.nastirlex.cinema.presentation.main.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetCollectionsCallback
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import kotlinx.coroutines.launch

class CollectionsViewModel : ViewModel() {

    private val collectionsRepositoryImpl by lazy { CollectionsRepositoryImpl() }

    private val _collections = MutableLiveData<List<CollectionDto>>()
    val collections: LiveData<List<CollectionDto>>
        get() = _collections

    init {
        getCollections()
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