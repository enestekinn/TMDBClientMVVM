package com.anushka.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.data.domain.usercase.GetArtistsUseCase
import com.anushka.tmdbclient.data.domain.usercase.UpdateArtistsUseCase
import com.anushka.tmdbclient.data.domain.usercase.UpdateMoviesUseCase
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.movie.Movie

class ArtistViewModel(
    private val getArtistUse: GetArtistsUseCase,
    private val updateArtistUsecase: UpdateArtistsUseCase
) : ViewModel() {

    fun getMovies() = liveData {
        val artistList : List<Artist>? = getArtistUse.execute()
        emit(artistList)
    }

    fun updateArtist() = liveData{
        val artistList = updateArtistUsecase.execute()
        emit(artistList)

    }

}