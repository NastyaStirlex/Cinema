package com.nastirlex.cinema.data.utils

import androidx.annotation.StringRes

sealed class Resource<T>(val data: T? = null, @StringRes val message: Int? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(@StringRes message: Int?) : Resource<T>(message = message)
    class Loading<T>() : Resource<T>()
    class Default<T>: Resource<T>()
}