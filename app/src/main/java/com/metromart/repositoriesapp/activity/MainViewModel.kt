package com.metromart.repositoriesapp.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metromart.repositoriesapp.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val listRepository: ListRepository
) : ViewModel() {

    private val mutableMainState: MutableSharedFlow<MainState> = MutableSharedFlow()
    val mainState = mutableMainState.asSharedFlow()

    fun getRepositories() {
        viewModelScope.launch {
            mutableMainState.emit(MainState.ShowLoader)
            val repositories = listRepository.getRepositories()
            mutableMainState.emit(MainState.LoadRepositories(repositories = repositories))
        }
    }

}