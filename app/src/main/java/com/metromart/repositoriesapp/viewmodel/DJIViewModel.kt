package com.zw.clearspotaidemo.models

import androidx.lifecycle.ViewModel
import com.zw.clearspotaidemo.util.DJIToastUtil
import dji.v5.utils.common.LogUtils

open class DJIViewModel : ViewModel() {
    val toastResult
        get() = DJIToastUtil.dJIToastLD

    val logTag = LogUtils.getTag(this)

}