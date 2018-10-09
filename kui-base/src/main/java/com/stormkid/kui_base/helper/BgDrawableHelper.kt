package com.stormkid.kui_base.helper

import android.graphics.drawable.Drawable

/**
设置可用背景工具
@author ke_li
@date 2018/10/8
 */
class BgDrawableHelper (private val bgDrawableInterf: BgDrawableInterf){

    fun setCircleDrawable(circleDrawable:Drawable){
        bgDrawableInterf.getCircleBg(circleDrawable)
    }


    fun setRadiusDrawable(radiusDrawable: Drawable){
        bgDrawableInterf.getRadiusBg(radiusDrawable)
    }

}