package com.aritra.gifbucket.domain.usecase

import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.utils.Resource
import com.aritra.gifbucket.data.utils.Status
import com.aritra.gifbucket.domain.repository.SearchRepository
import javax.inject.Inject

class GifSearchUseCaseImpl @Inject constructor(val searchRepository: SearchRepository) :
    GifSearchUseCase {
    override suspend fun searchGif(queryString: String): Resource<GifSearchResponse> {
        val repoResponse = searchRepository.searchGif(queryString)
        // write your business logic
        // here Im just converting the output to a more UI friendly data set. Just for example
        // u can implement whole lot of logic here
        when (repoResponse.status) {
            Status.SUCCESS -> {
                val actualSearchResult = repoResponse.data
                // do ur operation
                return repoResponse
            }
            Status.ERROR -> return repoResponse
            Status.LOADING -> return repoResponse // faltu likhte holo
        }
    }
}