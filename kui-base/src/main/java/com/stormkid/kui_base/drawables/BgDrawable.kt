package com.stormkid.kui_base.drawables

import android.graphics.drawable.GradientDrawable
import android.view.View
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

    fun getCircleDrawable(){

    }

    fun getRadiusDrawable(view: View){
        val context = view.context
        val radius = DimenUtils.px2dip(context,this.radius)
    }

}