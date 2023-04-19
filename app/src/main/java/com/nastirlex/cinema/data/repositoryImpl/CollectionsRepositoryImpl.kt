package com.nastirlex.cinema.data.repositoryImpl

import android.util.Log
import com.nastirlex.cinema.data.callbacks.GetCollectionCallback
import com.nastirlex.cinema.data.callbacks.GetCollectionsCallback
import com.nastirlex.cinema.data.callbacks.GetMoviesCallback
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.CollectionAbbreviateDto
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repository.CollectionsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

class CollectionsRepositoryImpl : CollectionsRepository {

    private var token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMDc2ODQud2ViLmhvc3RpbmctcnVzc2lhLnJ1IiwiZXhwIjoxNjgxOTE0MTczLCJLRVlfQ0xBSU1fVVNFUiI6IjdjNjUzNTZmLWEzZWUtNGRkYy05ZmIxLWZmOGRjMWYyZmQxYSJ9.g9fIQRAGzMJ_lw9rhRjmP2z7NAnZG7IiGIrymf-NdNY"


    private var callGetCollections: Call<List<CollectionDto>>? = null
    override fun getCollections(callback: GetCollectionsCallback<List<CollectionDto>>) {
        callGetCollections = ApiClient.collectionsApiService.getCollections(token = "Bearer $token")
        callGetCollections?.enqueue(
            object : Callback<List<CollectionDto>> {
                override fun onResponse(
                    call: Call<List<CollectionDto>>,
                    response: Response<List<CollectionDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                collections = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<List<CollectionDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    private var callCreateCollection: Call<CollectionDto>? = null
    override fun createCollection(
        collectionAbbreviateDto: CollectionAbbreviateDto,
        callback: GetCollectionCallback<CollectionDto>
    ) {
        callCreateCollection =
            ApiClient.collectionsApiService.createCollection(
                token = "Bearer $token",
                collectionAbbreviateDto = collectionAbbreviateDto
            )
        callCreateCollection?.enqueue(
            object : Callback<CollectionDto> {
                override fun onResponse(
                    call: Call<CollectionDto>,
                    response: Response<CollectionDto>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                collection = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<CollectionDto>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    override suspend fun deleteCollection(collectionId: String) {
        try {
            ApiClient.collectionsApiService.deleteCollection(
                token = "Bearer $token",
                collectionId = collectionId
            )

        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    //profileScreenState.value = Event.error(R.string.http_error)
                }
                is UnknownHostException, is SocketException -> {
                    //profileScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
        }
    }

    private var callGetCollectionFilms: Call<List<MovieDto>>? = null
    override fun getCollectionFilms(
        collectionId: String,
        callback: GetMoviesCallback<List<MovieDto>>
    ) {
        callGetCollectionFilms = ApiClient.collectionsApiService.getCollectionFilms(
            token = "Bearer $token",
            collectionId = collectionId
        )
        callGetCollectionFilms?.enqueue(
            object : Callback<List<MovieDto>> {
                override fun onResponse(
                    call: Call<List<MovieDto>>,
                    response: Response<List<MovieDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }
                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                movies = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            //when (response.code()) {
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                    //e.printStackTrace()
                                }
                            }
                            //callback.onError(response.code())
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")
                            return
                            //}
                            //callback.onError("Error")
                        }
                    }
                }

                override fun onFailure(call: Call<List<MovieDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }

    override suspend fun addFilmInCollection(collectionId: String, movieId: String) {
        try {
            ApiClient.collectionsApiService.addFilmInCollection(
                token = "Bearer $token",
                collectionId = collectionId,
                movieId = movieId
            )

        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    //profileScreenState.value = Event.error(R.string.http_error)
                }
                is UnknownHostException, is SocketException -> {
                    //profileScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
        }
    }

    override suspend fun deleteFilmFromCollection(collectionId: String, movieId: String) {
        try {
            ApiClient.collectionsApiService.deleteFilmFromCollection(
                token = "Bearer $token",
                collectionId = collectionId,
                movieId = movieId
            )

        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    //profileScreenState.value = Event.error(R.string.http_error)
                }
                is UnknownHostException, is SocketException -> {
                    //profileScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
        }
    }
}