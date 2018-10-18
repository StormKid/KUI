package com.stormkid.kui_base.drawables

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt
import com.stormkid.kui_base.InitDrawable
import com.stormkid.kui_base.dimen.DimenUtils

/**

@author ke_li
@date 2018/10/8
 */
class BgDrawable private constructor() : GradientDrawable() {


    companion object {
        val instance by lazy { BgDrawable() }
    }

    private fun build() = BgDrawable()

    fun initbg(@ColorInt colorInt: Int) {
        setColor(colorInt)
    }

    fun initBordBg(colorInt: Int) {
        setColor(Color.WHITE)
        setStroke(2,colorInt)
    }



    /**
     * 获取圆形背景
     */
    fun getCircleDrawable(initDrawable: InitDrawable) {
        build().apply {
            shape = OVAL
            val color = initDrawable.colorRes
            val view = initDrawable.view
            if (initDrawable.isStroke)initBordBg(color)
            else initbg(color)
            view.background = this
        }
    }

    /**
     * 获取带有边角的背景
     */
    fun getRadiusDrawable(initDrawable: InitDrawable) {
        build().apply {
            val context = initDrawable.view.context
            val color = initDrawable.colorRes
            val radius = DimenUtils.dip2px(context, initDrawable.radius)
            cornerRadius = radius.toFloat()
            val view = initDrawable.view
            if (initDrawable.isStroke) initBordBg(color)
            else initbg(color)
            view.background = this
        }
    }

    /**
     * 获取两侧是圆形，中间长的背景
     */
    fun getRoundDrawable(initDrawable: InitDrawable) {
        build().apply {
            val height = initDrawable.view.measuredHeight
            val radius = height / 2f
            val color = initDrawable.colorRes
            cornerRadius = radius
            val view = initDrawable.view
            if (initDrawable.isStroke)initBordBg(color)
            else initbg(color)
            view.background = this
        }
    }

}