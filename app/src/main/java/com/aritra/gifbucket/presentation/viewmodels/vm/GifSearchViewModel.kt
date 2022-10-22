package com.aritra.gifbucket.presentation.viewmodels.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.utils.Resource
import com.aritra.gifbucket.domain.usecase.GifSearchUseCase

class GifSearchViewModel(private val gifSearchUseCase: GifSearchUseCase): ViewModel() {

    fun getGifSearchResult(searchQuery: String) = liveData<Resource<GifSearchResponse>> {
        emit(Resource.loading(null))
        val response = gifSearchUseCase.searchGif(searchQuery)
        emit(response)
    }
}