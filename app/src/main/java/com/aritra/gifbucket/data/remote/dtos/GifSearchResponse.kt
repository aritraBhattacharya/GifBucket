package com.aritra.gifbucket.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class GifSearchResponse(
    @SerializedName("data")
    val gifData: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)