package com.stormkid.kui_base.drawables

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import androidx.annotation.ColorInt

/**
可任意控制颜色水波纹drawable
@author ke_li
@date 2018/9/30
 */
class RippleDrawable(@ColorInt color: Int,limitDrawable: Drawable) : RippleDrawable(ColorStateList.valueOf(color),limitDrawable,limitDrawable)
