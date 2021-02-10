package com.anushka.tmdbclient.data.repository.tvshow

import android.util.Log
import com.anushka.tmdbclient.data.domain.repository.ArtistRepository
import com.anushka.tmdbclient.data.domain.repository.MovieRepository
import com.anushka.tmdbclient.data.domain.repository.TvShowRepository
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowsLocalDataSource
import java.lang.Exception

class TvShowRepositoryImpl(
    private val tvshowRemoteDataSource: TvShowRemoteDataSource,
    private val tvshowLocalDataSource: TvShowsLocalDataSource,
    private val tvshowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? {

        return getTvShowsFromCache()

    }

    override suspend fun updateTvShows(): List<TvShow>? {

        val newListOfTvShows = getTvShowsFromAPI()
        tvshowLocalDataSource.clearAll()
        tvshowLocalDataSource.saveTvShowToDB(newListOfTvShows)
        tvshowCacheDataSource.saveTvShowToCache(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvShowsFromAPI(): List<TvShow>{
lateinit var tvshowList: List<TvShow>
        try {
            val response = tvshowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body != null) {
                tvshowList = body.tvShows
            }
        }catch (exception: Exception) {

            Log.i("MyTag",exception.message.toString())
        }
        return tvshowList
    }

    suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvshowList: List<TvShow>
        try {
            val response = tvshowLocalDataSource.getTvShowFromDB()


        }catch (exception: Exception) {

            Log.i("MyTag",exception.message.toString())
        }
        if (tvshowList.size > 0) {
            return tvshowList

        }else {
            tvshowList = getTvShowsFromAPI()
            tvshowLocalDataSource.saveTvShowToDB(tvshowList)
        }
        return  tvshowList
    }



suspend fun getTvShowsFromCache(): List<TvShow> {
    lateinit var tvshowList: List<TvShow>
    try {
        tvshowList = tvshowCacheDataSource.getTvShowFromCache()


    }catch (exception: Exception) {

        Log.i("MyTag",exception.message.toString())
    }
    if (tvshowList.size > 0) {
        return tvshowList

    }else {
        tvshowList = getTvShowsFromDB()
       tvshowCacheDataSource.saveTvShowToCache(tvshowList)
    }
    return  tvshowList
}


}
