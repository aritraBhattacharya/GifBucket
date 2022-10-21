package com.aritra.gifbucket.data.remote.network

import android.provider.SyncStateContract
import com.aritra.gifbucket.BuildConfig
import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifAPI {

    // search gif based on key word
    //https://api.giphy.com/v1/gifs/search?api_key=l6G2PlPHwvBcRjEwy3otibNvtzwSVHiy&q=beach&limit=25&offset=0&rating=g&lang=en
        @GET("/v1/gifs/search")
        suspend fun getSearchedGif(
        @Query("api_key")api_key:String=BuildConfig.API_KEY,
        @Query("q")q:String,
        @Query("limit")limit:String="25",
        @Query("offset")offset:String="0",
        @Query("lang")lang:String="en",
        ): Response<GifSearchResponse>

}