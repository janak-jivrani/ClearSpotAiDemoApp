package com.metromart.repositoriesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.metromart.repositoriesapp.R
import com.zw.clearspotaidemo.pages.CameraStreamDetailFragment
import dji.sdk.keyvalue.value.common.ComponentIndexType

class CameraStreamListFragment : Fragment() {

    private lateinit var llCameraList: LinearLayout
    private lateinit var tvNoData : TextView
    private val viewModule: CameraStreamListVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_camera_stream_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        llCameraList = view.findViewById(R.id.ll_camera_preview_list)
        tvNoData = view.findViewById(R.id.tvNoData)
        viewModule.availableCameraListData.observe(viewLifecycleOwner) { availableCameraList ->
            updateAvailableCamera(availableCameraList)
            if (availableCameraList.isEmpty()) {
                tvNoData.visibility = View.VISIBLE
            } else {
                tvNoData.visibility = View.GONE
            }
        }
    }

    private fun updateAvailableCamera(availableCameraList: List<ComponentIndexType>) {
        var ft = childFragmentManager.beginTransaction()
        val fragmentList = childFragmentManager.fragments
        for (fragment in fragmentList) {
            ft.remove(fragment!!)
        }
        ft.commitAllowingStateLoss()
        llCameraList.removeAllViews()
        ft = childFragmentManager.beginTransaction()
        val onlyOneCamera = availableCameraList.size == 1
        for (cameraIndex in availableCameraList) {
            val frameLayout = FrameLayout(llCameraList.context)
            frameLayout.id = View.generateViewId()
            val lp = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f)
            llCameraList.addView(frameLayout, lp)
            ft.replace(frameLayout.id, CameraStreamDetailFragment.newInstance(cameraIndex, onlyOneCamera), cameraIndex.name)
        }
        ft.commitAllowingStateLoss()
    }
}