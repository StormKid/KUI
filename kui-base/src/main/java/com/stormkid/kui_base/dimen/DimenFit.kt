package com.stormkid.kui_base.dimen

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration

/**
轻量屏幕适配
@author ke_li
@date 2018/9/25
 */
class DimenFit private constructor(){
    /**
     * 系统的density
     */
    private var sysDensity = 0f
    /**
     * 系统的ScaleDensity
     */
    private var sysScaleDensity = 0f

    /**
     * 指定设计屏幕尺寸
     */
    private var selfScreen = 360f

    companion object {
         val instance by  lazy { DimenFit() }
    }

    fun initScreen(screenWidth:Float): DimenFit {
        this.selfScreen = screenWidth
        return this
    }

    fun setCustDensity(application: Application,activity: Activity){
        val displayMetrics = application.resources.displayMetrics
        if (sysDensity==0f){
            sysDensity = displayMetrics.density
            sysScaleDensity = displayMetrics.scaledDensity
            application.registerComponentCallbacks(object :ComponentCallbacks{
                override fun onLowMemory() {

                }

                override fun onConfigurationChanged(newConfig: Configuration?) {
                    if (newConfig!=null&&newConfig.fontScale>0){
                        sysScaleDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

            })
        }

        val targetDensity = displayMetrics.widthPixels / selfScreen
        val targetScaleDensity = targetDensity*(sysScaleDensity/sysDensity)
        val dpi = targetDensity*160
        displayMetrics.density = targetDensity
        displayMetrics.densityDpi = dpi.toInt()
        displayMetrics.scaledDensity = targetScaleDensity

        val actDisplayMetrics = activity.resources.displayMetrics
        actDisplayMetrics.density = targetDensity
        actDisplayMetrics.densityDpi = dpi.toInt()
        actDisplayMetrics.scaledDensity = targetScaleDensity
    }


}