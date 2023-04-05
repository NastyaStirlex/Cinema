package com.nastirlex.cinema.data.repositoryImpl

import android.util.Log
import com.nastirlex.cinema.data.callbacks.GetCollectionsCallback
import com.nastirlex.cinema.data.di.ApiClient
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.repository.CollectionsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class CollectionsRepositoryImpl : CollectionsRepository {

    private var token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMDc2ODQud2ViLmhvc3RpbmctcnVzc2lhLnJ1IiwiZXhwIjoxNjgwNjgwNTMwLCJLRVlfQ0xBSU1fVVNFUiI6ImViZTgwOTg2LTA4ZmQtNDE1Yi1hNzk0LWYyYWIwOTAwOTdkMCJ9.w5FGs9719yUYfItsHFWLcLFh3MtdzmeDalP9Po8xWso"


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
}