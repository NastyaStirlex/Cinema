package com.nastirlex.cinema.presentation.sign_up

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.database.entity.Collection
import com.nastirlex.cinema.database.repositoryImpl.CollectionDatabaseRepositoryImpl
import com.nastirlex.cinema.presentation.main.Event
import com.nastirlex.cinema.presentation.main.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel(private val application: Application) : ViewModel() {

    private val authRepositoryImpl by lazy { AuthRepositoryImpl() }

    private val collectionDatabaseRepositoryImpl by lazy {
        CollectionDatabaseRepositoryImpl(
            application
        )
    }

    private val _signUpScreenState = MutableLiveData<Resource<TokenDto>>()
    val signUpScreenState: MutableLiveData<Resource<TokenDto>>
        get() = _signUpScreenState

    fun onClickRegister(registerBody: RegisterBodyDto) = viewModelScope.launch(Dispatchers.IO) {
        try {
            authRepositoryImpl.register(
                registerBody = registerBody,
                _signUpScreenState
            )

            collectionDatabaseRepositoryImpl.insertCollection(
                Collection(
                    name = "Favourites",
                    icon = R.drawable.ic_heart
                )
            )

        } catch (e: Exception) {
        }


    }
}