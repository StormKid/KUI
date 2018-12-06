package com.stormkid.kui_base.toast

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.WindowManager
import android.view.animation.AnimationSet

/**
自定义弹出土司提示
@author ke_li
@date 2018/10/23
 */
class KuiToast (private val context: Context){

    private val kuiToastView = KuiToastView(context)
    private val layoutparams = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
        flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        windowAnimations = AnimationSet.ZORDER_TOP
        gravity = Gravity.BOTTOM
    }

    fun showToast(){
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        manager.addView(kuiToastView,layoutparams)
    }




}