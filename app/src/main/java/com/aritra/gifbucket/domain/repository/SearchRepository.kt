package com.aritra.gifbucket.domain.repository

import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.utils.Resource
import retrofit2.Response

interface SearchRepository {

    suspend fun searchGif(queryString: String): Resource<GifSearchResponse>
}