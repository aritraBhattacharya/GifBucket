package com.aritra.gifbucket.data.models

data class GifSearchResponse(
    val gifData: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)