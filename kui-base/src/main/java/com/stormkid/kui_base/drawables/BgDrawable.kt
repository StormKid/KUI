package com.stormkid.kui_base.drawables

import android.graphics.drawable.GradientDrawable
import com.stormkid.kui_base.InitDrawable
import com.stormkid.kui_base.dimen.DimenUtils

/**

@author ke_li
@date 2018/10/8
 */
class BgDrawable private constructor() : GradientDrawable() {

    private var radius = 10f

    companion object {
        val instance by lazy { BgDrawable() }
    }

    private fun build() = BgDrawable()


    /**
     * 获取圆形背景
     */
    fun getCircleDrawable(initDrawable: InitDrawable) {
        build().apply {
            shape = OVAL
            val context = initDrawable.view.context
            val color = initDrawable.colorRes
            setColor(color)
            val view = initDrawable.view
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
            val radius = DimenUtils.px2dip(context, this.radius)
            setColor(color)
            cornerRadius = radius.toFloat()
            val view = initDrawable.view
            view.background = this
        }
    }

    /**
     * 获取两侧是圆形，中间长的背景
     */
    fun getRoundDrawable(initDrawable: InitDrawable) {
        build().apply {
            val height = initDrawable.view.measuredHeight
            radius = height / 2f
            val color = initDrawable.colorRes
            setColor(color)
            cornerRadius = radius
            val view = initDrawable.view
            view.background = this
        }
    }

}