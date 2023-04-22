package com.nastirlex.cinema.presentation.main.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetProfileCallback
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.ProfileDto
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.ProfileRepositoryImpl
import com.nastirlex.cinema.database.CollectionDatabase_Impl
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val application: Application) : ViewModel() {

    private val profileRepositoryImpl by lazy { ProfileRepositoryImpl(application) }
    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }
    private val collectionDatabaseRepositoryImpl by lazy {
        CollectionDatabaseRepositoryImpl(
            application
        )
    }

    private var _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private var _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private var _avatar = MutableLiveData<String?>()
    val avatar: LiveData<String?>
        get() = _avatar

    init {
        getProfile()
    }

    private fun getProfile() = viewModelScope.launch {
        profileRepositoryImpl.getProfile(
            object : GetProfileCallback<ProfileDto> {
                override fun onSuccess(profile: ProfileDto) {
                    _name.value = profile.firstName + " " + profile.lastName
                    _email.value = profile.email
                    _avatar.value = profile.avatar
                }

                override fun onError(error: String?) {}
            }
        )
    }

    fun logout() = viewModelScope.launch(Dispatchers.IO) {
        jwtRepositoryImpl.deleteAccessToken(application.applicationContext)
        jwtRepositoryImpl.deleteRefreshToken(application.applicationContext)
        jwtRepositoryImpl.deleteUserId(application.applicationContext)
        collectionDatabaseRepositoryImpl.cleanCollectionsTable(collectionDatabaseRepositoryImpl.getFavouritesId())
        collectionDatabaseRepositoryImpl.cleatFilmsTable()
    }
}