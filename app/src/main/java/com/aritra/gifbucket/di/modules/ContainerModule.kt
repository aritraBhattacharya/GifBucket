package com.aritra.gifbucket.di.modules

import com.aritra.gifbucket.domain.repository.SearchRepository
import com.aritra.gifbucket.domain.repository.SearchRepositoryImpl
import com.aritra.gifbucket.domain.usecase.GifSearchUseCase
import com.aritra.gifbucket.domain.usecase.GifSearchUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ContainerModule {

    @Binds
    abstract fun getSearchRepository(searchRepositoryImpl: SearchRepositoryImpl):SearchRepository
    // need to provide repo, usecase separately as they are interface
    // and we need to specify which implementation should be provided
    @Binds
    abstract fun getSearchUseCase(gifSearchUseCaseImpl: GifSearchUseCaseImpl):GifSearchUseCase


}