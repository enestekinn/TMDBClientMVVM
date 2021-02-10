package com.anushka.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.data.domain.usercase.*
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.tvshow.TvShow

class TvShowViewModel(
    private val getTvShowUse: GetTvShowsUseCase,
    private val updateTvShowUsecase: UpdateTvShowsUseCase
) : ViewModel() {

    fun getTvShows() = liveData {
        val tvshowList : List<TvShow>? = getTvShowUse.execute()
        emit(tvshowList)
    }

    fun updateTvShow() = liveData{
        val tvshowList = updateTvShowUsecase.execute()
        emit(tvshowList)

    }

}