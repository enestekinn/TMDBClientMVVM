package com.anushka.tmdbclient.data.repository.tvshow.datasource

import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface TvShowsLocalDataSource {

    suspend fun getTvShowFromDB(): List<TvShow>
    suspend fun saveTvShowToDB(movies: List<TvShow>)
    suspend fun clearAll()
}