package com.aritra.gifbucket.data.remote.dtos

data class GifSearchResponse(
    val gifData: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)