package com.nastirlex.cinema.utils

import android.content.Context
import android.util.DisplayMetrics

fun Context.dpToPixel(dp: Float): Float {
    return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}