package com.nastirlex.cinema.presentation.main.collections.collection_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.callbacks.GetCollectionCallback
import com.nastirlex.cinema.data.dto.CollectionAbbreviateDto
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import com.nastirlex.cinema.presentation.main.Event
import kotlinx.coroutines.launch

class CollectionCreateViewModel(private var collectionsRepositoryImpl: CollectionsRepositoryImpl) :
    ViewModel() {

    private val _collectionCreateScreenState = MutableLiveData<Event<Any>>()
    val collectionCreateScreenState: LiveData<Event<Any>>
        get() = _collectionCreateScreenState

    val icon = MutableLiveData(2131165363)

    fun createCollection(name: String) = viewModelScope.launch {
        collectionsRepositoryImpl.createCollection(
            collectionAbbreviateDto = CollectionAbbreviateDto(name = name),
            object : GetCollectionCallback<CollectionDto> {
                override fun onSuccess(collection: CollectionDto) {
                    _collectionCreateScreenState.value = Event.success(null)
                }

                override fun onError(error: String?) {
                    _collectionCreateScreenState.value = Event.error(R.string.error_unknown)
                }

            }
        )
    }

    fun updateIcon(newIcon: Int) {
        icon.value = newIcon
    }
}