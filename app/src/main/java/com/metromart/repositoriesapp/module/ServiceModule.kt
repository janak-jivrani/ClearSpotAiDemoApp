package com.metromart.repositoriesapp.module

import com.metromart.repositoriesapp.network.RepositoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Singleton
    @Provides
    fun provideRepositoryService(retrofit: Retrofit): RepositoryService = retrofit.create(RepositoryService::class.java)
}
