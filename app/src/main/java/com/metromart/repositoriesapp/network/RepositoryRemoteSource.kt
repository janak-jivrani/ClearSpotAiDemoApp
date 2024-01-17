package com.metromart.repositoriesapp.network

import com.metromart.repositoriesapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryRemoteSource @Inject constructor(
    private val repositoryService: RepositoryService
) {

    suspend fun getRepositories(): List<Repository> =
        withContext(Dispatchers.IO) {
            repositoryService.getRepositories()
        }
}
