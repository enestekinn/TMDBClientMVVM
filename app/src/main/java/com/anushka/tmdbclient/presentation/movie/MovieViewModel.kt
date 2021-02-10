package com.anushka.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.data.domain.usercase.GetMoviesUseCase
import com.anushka.tmdbclient.data.domain.usercase.UpdateMoviesUseCase
import com.anushka.tmdbclient.data.model.movie.Movie

class MovieViewModel(
    private val getMoviesUse: GetMoviesUseCase,
    private val updateMoviesUsercase: UpdateMoviesUseCase
) : ViewModel() {

    fun getMovies() = liveData {
        val movieList : List<Movie>? = getMoviesUse.execute()
        emit(movieList)
    }

    fun updateMovies() = liveData{
        val movieList = updateMoviesUsercase.execute()
        emit(movieList)

    }

}