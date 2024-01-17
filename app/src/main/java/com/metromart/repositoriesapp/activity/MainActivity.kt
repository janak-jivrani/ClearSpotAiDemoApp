package com.metromart.repositoriesapp.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.metromart.repositoriesapp.databinding.ActivityMainBinding
import com.metromart.repositoriesapp.extensions.gone
import com.metromart.repositoriesapp.extensions.visible
import com.metromart.repositoriesapp.fragments.ImagesFragment
import com.zw.clearspotaidemo.util.FragmentNavUtils
import dagger.hilt.android.AndroidEntryPoint
import dji.v5.common.error.IDJIError
import dji.v5.common.register.DJISDKInitEvent
import dji.v5.manager.SDKManager
import dji.v5.manager.interfaces.SDKManagerCallback
import dji.v5.utils.common.LogUtils
import dji.v5.utils.common.PermissionUtil

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "myApp"
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    val tag: String = LogUtils.getTag(this)
    private val permissionArray = arrayListOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.KILL_BACKGROUND_PROCESSES,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    init {
        permissionArray.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.READ_MEDIA_IMAGES)
                add(Manifest.permission.READ_MEDIA_VIDEO)
                add(Manifest.permission.READ_MEDIA_AUDIO)
            } else {
                add(Manifest.permission.READ_EXTERNAL_STORAGE)
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermissionAndRequest()
        registerApp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!checkPermission()) {
            checkPermissionAndRequest()
        }
    }

    private fun registerApp() {
        SDKManager.getInstance().init(this, object : SDKManagerCallback {
            override fun onRegisterSuccess() {
                runOnUiThread {
                    binding.progressBar.gone()
                    binding.fragmentContainer.visible()
                    FragmentNavUtils.loadFragment(ImagesFragment(),this@MainActivity)
                }
                Log.i(TAG, "myApp onRegisterSuccess")
            }

            override fun onRegisterFailure(error: IDJIError) {
                Log.i(TAG, "myApp onRegisterFailure")
            }

            override fun onProductDisconnect(productId: Int) {
                Log.i(TAG, "myApp onProductDisconnect")
            }

            override fun onProductConnect(productId: Int) {
                Log.i(TAG, "myApp onProductConnect")
            }

            override fun onProductChanged(productId: Int) {
                Log.i(TAG, "myApp onProductChanged")
            }

            override fun onInitProcess(event: DJISDKInitEvent, totalProcess: Int) {
                Log.i(TAG, "myApp onInitProcess")
                if (event == DJISDKInitEvent.INITIALIZE_COMPLETE) {
                    Log.i(TAG, "myApp start registerApp")
                    SDKManager.getInstance().registerApp()
                }
            }

            override fun onDatabaseDownloadProgress(current: Long, total: Long) {
                Log.i(TAG, "myApp onDatabaseDownloadProgress")
            }
        })
    }


    private fun checkPermissionAndRequest() {
        if (!checkPermission()) {
            requestPermission()
        }
    }


    private fun checkPermission(): Boolean {
        for (i in permissionArray.indices) {
            if (!PermissionUtil.isPermissionGranted(this, permissionArray[i])) {
                return false
            }
        }
        return true
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        result?.entries?.forEach {
            if (!it.value) {
                requestPermission()
                return@forEach
            }
        }
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(permissionArray.toArray(arrayOf()))
    }


}
