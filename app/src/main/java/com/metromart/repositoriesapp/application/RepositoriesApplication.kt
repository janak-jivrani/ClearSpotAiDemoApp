package com.metromart.repositoriesapp.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RepositoriesApplication : Application() {
	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		com.secneo.sdk.Helper.install(this)
	}
}