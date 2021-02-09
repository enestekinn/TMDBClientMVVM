package com.anushka.tmdbclient.data.domain.usercase

import com.anushka.tmdbclient.data.domain.repository.ArtistRepository
import com.anushka.tmdbclient.data.model.artist.Artist

class GetArtistsUserCase(private val artistRepository: ArtistRepository) {

    suspend fun execute(): List<Artist>? = artistRepository.getArtist()




}