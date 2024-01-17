package com.metromart.repositoriesapp.extensions

import android.graphics.Color
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.setCustomColorSchemeColors() {
    setColorSchemeColors(
        Color.GREEN,
        Color.BLUE,
        Color.YELLOW,
        Color.RED
    )
}

fun SwipeRefreshLayout.setRefreshState(isRefreshing: Boolean) {
    this.post { this.isRefreshing = isRefreshing }
}
