package com.example.mylearningdemo.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import com.example.mylearningdemo.App

object ScreenUtils {

    fun getScreenWidth(context: Context): Int {
        val display = context.resources.displayMetrics
        val width = display.widthPixels
        return width
    }

    fun getScreenHeight(context: Context): Int {
        val display = context.resources.displayMetrics
        val height = display.heightPixels
        return height
    }


}