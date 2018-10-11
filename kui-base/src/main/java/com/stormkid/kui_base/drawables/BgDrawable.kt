package com.stormkid.kui_base.drawables

import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import com.stormkid.kui_base.InitDrawable
import com.stormkid.kui_base.dimen.DimenUtils

/**

@author ke_li
@date 2018/10/8
 */
class BgDrawable private constructor(): GradientDrawable() {

    private var radius = 10f

    companion object {
        val instance by lazy { BgDrawable() }
    }

    /**
     * 获取圆形背景
     */
    fun getCircleDrawable(initDrawable: InitDrawable){
        shape = OVAL
        val context = initDrawable.view.context
        val color = initDrawable.colorRes
        setColor(ContextCompat.getColor(context,color))
    }

    /**
     * 获取带有边角的背景
     */
    fun getRadiusDrawable(initDrawable: InitDrawable){
        val context = initDrawable.view.context
        val color = initDrawable.colorRes
        val radius = DimenUtils.px2dip(context,this.radius)
        setColor(ContextCompat.getColor(context,color))
        cornerRadius = radius.toFloat()
    }

    /**
     * 获取两侧是圆形，中间长的背景
     */
    fun getLeftRightCircleDrawable(initDrawable: InitDrawable){
        val height = initDrawable.view.height
        val context = initDrawable.view.context
        this.radius = height/2f
        val color = initDrawable.colorRes
        setColor(ContextCompat.getColor(context,color))
        cornerRadius = radius
    }
}