package com.anushka.tmdbclient.data.domain.usercase

import com.anushka.tmdbclient.data.domain.repository.TvShowRepository
import com.anushka.tmdbclient.data.model.tvshow.TvShow

class GetTvShowsUserCase(private val tvShowRepository: TvShowRepository) {

    suspend fun execute(): List<TvShow>? = tvShowRepository.getTvShows()




}