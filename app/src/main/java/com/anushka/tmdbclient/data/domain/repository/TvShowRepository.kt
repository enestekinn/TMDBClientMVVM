package com.anushka.tmdbclient.data.domain.repository

import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface TvShowRepository {

    suspend fun getTvShows(): List<TvShow>?

    suspend fun updateTvShows(): List<TvShow>?


}