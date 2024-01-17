package com.zw.clearspotaidemo.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.metromart.repositoriesapp.R


object FragmentNavUtils {
    fun loadFragment(fragment: Fragment, appCompatActivity: FragmentActivity) {
        val transaction: FragmentTransaction = appCompatActivity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    fun addFragment(fragment: Fragment, appCompatActivity: FragmentActivity) {
        val transaction: FragmentTransaction = appCompatActivity.supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment, appCompatActivity: FragmentActivity) {
        val transaction: FragmentTransaction = appCompatActivity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
