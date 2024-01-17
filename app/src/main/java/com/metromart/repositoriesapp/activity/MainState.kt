package com.metromart.repositoriesapp.activity

import com.metromart.repositoriesapp.domain.Repository

sealed class MainState {
    object ShowLoader : MainState()
    data class LoadRepositories(val repositories: List<Repository>) : MainState()
}