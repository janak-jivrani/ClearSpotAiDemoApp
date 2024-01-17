package com.metromart.repositoriesapp.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metromart.repositoriesapp.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dji.v5.utils.common.ContextUtil
import dji.v5.utils.common.DiskUtil
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
) : ViewModel() {

    private val mutableMainState: MutableSharedFlow<List<File>> = MutableSharedFlow()
    val mainState = mutableMainState.asSharedFlow()

    fun loadImages() {
        viewModelScope.launch {
            val dirs = File(
                DiskUtil.getExternalCacheDirPath(
                    ContextUtil.getContext(),
                    "CameraStreamImageDir"
                )
            )
            val fileList = dirs.listFiles()?.toList()
            mutableMainState.emit(fileList ?: arrayListOf())
        }
    }

}