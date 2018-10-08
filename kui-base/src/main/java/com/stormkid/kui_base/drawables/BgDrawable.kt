package com.stormkid.kui_base.drawables

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import com.stormkid.kui_base.helper.BgDrawableHelper
import com.stormkid.kui_base.helper.BgDrawableInterf

/**

@author ke_li
@date 2018/10/8
 */
class BgDrawable : GradientDrawable() ,BgDrawableInterf{

    private val helper:BgDrawableHelper =  BgDrawableHelper(this)

    override fun getCircleBg(bg: Drawable) {
    }

    override fun getRadiusBg(bg: Drawable) {
    }

}