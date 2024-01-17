package com.metromart.repositoriesapp.repository

import com.metromart.repositoriesapp.domain.Repository

interface ListRepository {
    suspend fun getRepositories(): List<Repository>
}
