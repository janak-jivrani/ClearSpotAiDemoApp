package com.metromart.repositoriesapp.repository

import com.metromart.repositoriesapp.domain.Repository
import com.metromart.repositoriesapp.network.RepositoryRemoteSource
import javax.inject.Inject

class ListRepositoryImpl
@Inject constructor(
    private val repositoryRemoteSource: RepositoryRemoteSource
) : ListRepository {

    override suspend fun getRepositories(): List<Repository> {
        return repositoryRemoteSource.getRepositories()
    }
}
