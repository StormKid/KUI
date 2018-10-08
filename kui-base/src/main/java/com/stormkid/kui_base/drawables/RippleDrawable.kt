package com.stormkid.kui_base.drawables

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
可任意控制颜色水波纹drawable
@author ke_li
@date 2018/9/30
 */
class RippleDrawable : Drawable(){
    override fun draw(canvas: Canvas?) {
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int=PixelFormat.TRANSPARENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    

}