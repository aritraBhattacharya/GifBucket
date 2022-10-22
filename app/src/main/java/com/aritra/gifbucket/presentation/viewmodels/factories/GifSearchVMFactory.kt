package com.aritra.gifbucket.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aritra.gifbucket.domain.usecase.GifSearchUseCase
import com.aritra.gifbucket.presentation.viewmodels.vm.GifSearchViewModel
import javax.inject.Inject

class GifSearchVMFactory @Inject constructor(val gifSearchUseCase: GifSearchUseCase):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GifSearchViewModel(gifSearchUseCase) as T
    }
}