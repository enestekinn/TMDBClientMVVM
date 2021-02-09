package com.anushka.tmdbclient.data.model

import com.anushka.tmdbclient.data.model.Artist
import com.google.gson.annotations.SerializedName

data class ArtistList(

    @SerializedName("results")
    val artists: List<Artist>
)