package com.nastirlex.cinema.presentation.sign_up

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.database.entity.Collection
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.presentation.main.Event
import com.nastirlex.cinema.presentation.main.Status
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel(private val application: Application) : ViewModel() {

    private val authRepositoryImpl by lazy { AuthRepositoryImpl() }

    private val collectionDatabaseRepositoryImpl by lazy { CollectionDatabaseRepositoryImpl(application) }

    private val _signUpScreenState = MutableLiveData<Event<TokenDto>>()
    val signUpScreenState: MutableLiveData<Event<TokenDto>>
        get() = _signUpScreenState

    fun onClickRegister(registerBody: RegisterBodyDto) = viewModelScope.launch {
        try {
            authRepositoryImpl.register(
                registerBody = registerBody,
                _signUpScreenState
            )

            when (_signUpScreenState.value?.status) {
                Status.SUCCESS -> {
                    val favouritesId = collectionDatabaseRepositoryImpl.insertCollection(
                        Collection(
                            name = "Избранное",
                            icon = R.drawable.ic_heart
                        )
                    )


                }
                else -> {}
            }

        } catch (e: Exception) {}


    }
}