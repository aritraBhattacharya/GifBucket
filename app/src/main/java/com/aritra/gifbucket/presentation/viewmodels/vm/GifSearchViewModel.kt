package com.aritra.gifbucket.presentation.viewmodels.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.utils.Resource
import com.aritra.gifbucket.domain.usecase.GifSearchUseCase
import kotlinx.coroutines.flow.flow

class GifSearchViewModel(private val gifSearchUseCase: GifSearchUseCase): ViewModel() {

    fun getGifSearchResult(searchQuery: String) = liveData<Resource<GifSearchResponse>> {
        emit(Resource.loading(null))
        val response = gifSearchUseCase.searchGif(searchQuery)
        emit(response)
    }

    fun getGifSearchResultFlow(searchQuery: String) = flow<Resource<GifSearchResponse>> {
        emit(Resource.loading(null))
        val response = gifSearchUseCase.searchGif(searchQuery)
        emit(response)
    }
}