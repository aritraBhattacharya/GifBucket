package com.aritra.gifbucket.domain.repository

import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.remote.network.GifAPI
import com.aritra.gifbucket.data.utils.Resource
import com.aritra.gifbucket.data.utils.SafeAPIRequest
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(val gifAPI: GifAPI) : SearchRepository,
    SafeAPIRequest() {
    override suspend fun searchGif(queryString: String): Resource<GifSearchResponse> {

        return apiRequest { gifAPI.getSearchedGif(q = queryString) }

/*      If you dont want to use SafeApiRequest
        return try{
            delay(2000)
            val apiResponse = gifAPI.getSearchedGif(q=queryString)
            if(apiResponse.isSuccessful){
                Resource.success(apiResponse.body())
            } else{
                Resource.error(apiResponse.code().toString(),null)
            }
        } catch (e:Exception){
            Resource.error(""+e.message,null)
        }*/

    }
}