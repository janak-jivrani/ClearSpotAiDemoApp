package com.metromart.repositoriesapp.module

import com.metromart.repositoriesapp.network.RepositoryRemoteSource
import com.metromart.repositoriesapp.repository.ListRepository
import com.metromart.repositoriesapp.repository.ListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideListRepository(
        repositoryRemoteSource: RepositoryRemoteSource,
    ): ListRepository = ListRepositoryImpl(repositoryRemoteSource)

}
