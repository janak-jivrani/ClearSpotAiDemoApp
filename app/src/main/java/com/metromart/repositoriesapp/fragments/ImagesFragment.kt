package com.metromart.repositoriesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.recyclerview.widget.LinearLayoutManager
import com.metromart.repositoriesapp.adapter.ImageAdapter
import com.metromart.repositoriesapp.databinding.FragmentImagesBinding
import com.zw.clearspotaidemo.util.FragmentNavUtils
import dji.v5.utils.common.ContextUtil
import dji.v5.utils.common.DiskUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File

class ImagesFragment : Fragment() {

	lateinit var fragmentImagesBinding: FragmentImagesBinding
	val imageListViewModel : ImageListViewModel by viewModels()
	lateinit var imageAdapter : ImageAdapter
	val imageList = arrayListOf<File>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		fragmentImagesBinding = FragmentImagesBinding.inflate(inflater)
		return fragmentImagesBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//rvImageList
		fragmentImagesBinding.button.setOnClickListener {
			FragmentNavUtils.replaceFragment(CameraStreamListFragment(),requireActivity())
		}
		fragmentImagesBinding.rvImageList.layoutManager = LinearLayoutManager(requireContext())
		imageAdapter = ImageAdapter(imageList)
		fragmentImagesBinding.rvImageList.adapter = imageAdapter
		observeForList()
		imageListViewModel.loadImages()
	}

	private fun observeForList() {
		lifecycleScope.launch {
			imageListViewModel.mainState.collect {
				imageList.clear()
				imageList.addAll(it)
				imageAdapter.notifyDataSetChanged()
				if (imageList.isEmpty()) {
					fragmentImagesBinding.rvImageList.visibility = View.GONE
					fragmentImagesBinding.tvNoData.visibility = View.VISIBLE
				} else {
					fragmentImagesBinding.rvImageList.visibility = View.VISIBLE
					fragmentImagesBinding.tvNoData.visibility = View.GONE
				}
			}
		}
	}


}