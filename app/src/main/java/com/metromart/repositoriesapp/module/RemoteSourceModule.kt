package com.metromart.repositoriesapp.module

import com.metromart.repositoriesapp.network.RepositoryRemoteSource
import com.metromart.repositoriesapp.network.RepositoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteSourceModule {

    @Provides
    @Singleton
    fun provideBaseRemoteSource(repositoryService: RepositoryService) =
        RepositoryRemoteSource(repositoryService)
}
