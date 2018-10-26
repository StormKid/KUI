package com.stormkid.kui_base.helper

import android.graphics.Paint
import androidx.annotation.ColorInt

/**
初始化paint
@author ke_li
@date 2018/10/26
 */
object PaintHelper {
    fun getNormalPaint(@ColorInt color: Int) = Paint(Paint.ANTI_ALIAS_FLAG).apply {
           setColor(color)
    }
}