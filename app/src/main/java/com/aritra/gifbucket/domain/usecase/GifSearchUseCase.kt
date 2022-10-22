package com.aritra.gifbucket.domain.usecase

import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.utils.Resource

interface GifSearchUseCase {
    suspend fun searchGif(queryString: String): Resource<GifSearchResponse>
}