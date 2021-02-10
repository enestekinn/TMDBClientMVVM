package com.anushka.tmdbclient.data.repository.artist

import android.util.Log
import com.anushka.tmdbclient.data.domain.repository.ArtistRepository
import com.anushka.tmdbclient.data.domain.repository.MovieRepository
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import java.lang.Exception

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override suspend fun getArtist(): List<Artist>? {

        return getArtistFromCache()

    }

    override suspend fun updateArtist(): List<Artist>? {

        val newListOfArtists = getArtistFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDB(newListOfArtists)
        artistCacheDataSource.saveArtistToCache(newListOfArtists)
        return newListOfArtists
    }

    suspend fun getArtistFromAPI(): List<Artist>{
lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtist()
            val body = response.body()
            if (body != null) {
                artistList = body.artists
            }
        }catch (exception: Exception) {

            Log.i("MyTag",exception.message.toString())
        }
        return artistList
    }

    suspend fun getArtistFromDB(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistLocalDataSource.getArtistFromDB()


        }catch (exception: Exception) {

            Log.i("MyTag",exception.message.toString())
        }
        if (artistList.size > 0) {
            return artistList

        }else {
            artistList = getArtistFromAPI()
            artistLocalDataSource.saveArtistToDB(artistList)
        }
        return  artistList
    }



suspend fun getArtistFromCache(): List<Artist> {
    lateinit var artistList: List<Artist>
    try {
        artistList = artistCacheDataSource.getArtistFromCache()


    }catch (exception: Exception) {

        Log.i("MyTag",exception.message.toString())
    }
    if (artistList.size > 0) {
        return artistList

    }else {
        artistList = getArtistFromDB()
       artistCacheDataSource.saveArtistToCache(artistList)
    }
    return  artistList
}


}
