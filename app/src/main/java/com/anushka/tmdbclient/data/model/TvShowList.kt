package com.anushka.tmdbclient.data.model

import com.anushka.tmdbclient.data.model.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowList(
    @SerializedName("results")
    val tvShows: List<TvShow>

)