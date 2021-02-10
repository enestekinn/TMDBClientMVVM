package com.anushka.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.tmdbclient.data.domain.usercase.GetArtistsUseCase
import com.anushka.tmdbclient.data.domain.usercase.GetTvShowsUseCase
import com.anushka.tmdbclient.data.domain.usercase.UpdateArtistsUseCase
import com.anushka.tmdbclient.data.domain.usercase.UpdateTvShowsUseCase

class TvShowViewModelFactory(
    private val getTvShowUseCase: GetTvShowsUseCase,
    private val updateTvShowUseCase: UpdateTvShowsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  TvShowViewModel(
            getTvShowUseCase,
            updateTvShowUseCase
        ) as T
    }

}