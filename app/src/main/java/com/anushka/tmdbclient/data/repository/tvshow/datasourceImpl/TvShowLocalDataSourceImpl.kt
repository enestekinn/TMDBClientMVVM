package com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl

import com.anushka.tmdbclient.data.db.ArtistDao
import com.anushka.tmdbclient.data.db.TvShowDao
import com.anushka.tmdbclient.data.domain.repository.TvShowRepository
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowsLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao):
    TvShowsLocalDataSource {
    override suspend fun getTvShowFromDB(): List<TvShow> {
        return tvShowDao.getTvShows()
    }

    override suspend fun saveTvShowToDB(tvshows: List<TvShow>) {
       CoroutineScope(Dispatchers.IO).launch {
           tvShowDao.saveTvShows(tvshows)
       }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
          tvShowDao.deleteAllTvShows()
       }
    }


}