package com.stormkid.kui_base.toast

import android.content.Context
import android.graphics.PixelFormat
import android.view.WindowManager
import com.stormkid.kui_base.TransLateOrScaleAnimationModel
import com.stormkid.kui_base.helper.AnimationCallback
import com.stormkid.kui_base.helper.AnimationHelpers

/**
自定义弹出土司提示
@author ke_li
@date 2018/10/23
 */
class KuiToast (private val context: Context){

    private val kuiToastView: KuiToastView = KuiToastView(context)
    private val layoutparams = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
        flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    }

    fun showToast(){
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val helper =  AnimationHelpers(object : AnimationCallback{
            override fun onFinished() {
                manager.removeViewImmediate(kuiToastView)
            }

            override fun onStarted() {
                manager.addView(kuiToastView,layoutparams)
            }

        })
         helper.scaleAnimation(TransLateOrScaleAnimationModel(0f,1f,0f,0f,5000,kuiToastView))

    }




}