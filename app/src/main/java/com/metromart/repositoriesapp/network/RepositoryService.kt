package com.metromart.repositoriesapp.network

import com.metromart.repositoriesapp.domain.Repository
import retrofit2.http.GET

interface RepositoryService {

    @GET("repositories")
    suspend fun getRepositories(): List<Repository>
}
