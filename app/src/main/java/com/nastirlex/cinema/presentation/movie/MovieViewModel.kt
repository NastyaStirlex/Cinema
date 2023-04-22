package com.nastirlex.cinema.presentation.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cinema.data.callbacks.GetEpisodesCallback
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.websocket.ChatWebSocketListener
import com.nastirlex.cinema.websocket.WebsocketRepositoryImpl
import kotlinx.coroutines.launch

class MovieViewModel(
    private val application: Application,
    movieId: String,
    chatId: String,
    //chatWebSocketListener: ChatWebSocketListener
) : ViewModel() {

    private val movieRepositoryImpl by lazy { MovieRepositoryImpl(application) }
//    private val websocketRepositoryImpl by lazy {
//        WebsocketRepositoryImpl(
//            application,
//            chatWebSocketListener,
//            chatId
//        )
//    }

    private var _episodes = MutableLiveData<List<EpisodeDto>>()
    val episodes: LiveData<List<EpisodeDto>>
        get() = _episodes

    private var _firstEpisode = MutableLiveData<EpisodeDto>(null)
    val firstEpisode: LiveData<EpisodeDto>
        get() = _firstEpisode

    init {
        getEpisodes(movieId)
        //getWebsocket()
    }

    private fun getEpisodes(movieId: String) = viewModelScope.launch {
        movieRepositoryImpl.getEpisodes(
            movieId = movieId,
            object : GetEpisodesCallback<List<EpisodeDto>> {
                override fun onSuccess(episodes: List<EpisodeDto>) {
                    _episodes.value = episodes
                    if (episodes.isNotEmpty()) {
                        _firstEpisode.value = episodes[0]
                    }
                }

                override fun onError(error: String?) {}
            }
        )
    }

//    private fun getWebsocket() {
//        websocketRepositoryImpl.websocket()
//    }
}