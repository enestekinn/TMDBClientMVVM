package com.anushka.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.tmdbclient.data.domain.usercase.GetArtistsUseCase
import com.anushka.tmdbclient.data.domain.usercase.UpdateArtistsUseCase

class ArtistViewModelFactory(
    private val getArtistUseCase: GetArtistsUseCase,
    private val updateArtistUseCase: UpdateArtistsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  ArtistViewModel(
            getArtistUseCase,
            updateArtistUseCase
        ) as T
    }

}