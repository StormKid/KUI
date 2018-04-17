package com.stormkid.kui_base

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * 全屏幕适配工具
 * @author like
 * @date 2018-4-16
 */
final object DimenUtils {

    fun getResult(view: ViewGroup,context:Context){
        val height = context.resources.displayMetrics.heightPixels
        val width = context.resources.displayMetrics.widthPixels
        val params = FrameLayout.LayoutParams(width,height)
        view.layoutParams = params
    }




}