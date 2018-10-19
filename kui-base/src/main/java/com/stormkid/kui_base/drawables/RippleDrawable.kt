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
class RippleDrawable : RippleDrawable{


    /**
     * @param colorStateList 代码构建Selector
     * @param limitDrawable 限制水波纹的drawable
     * @param maskDrawable 限制显示背景的drawable
     */
    constructor(colorStateList: ColorStateList,limitDrawable: Drawable,maskDrawable: Drawable):super(colorStateList,limitDrawable,maskDrawable)


    constructor(@ColorInt color: Int,limitDrawable: Drawable):this(ColorStateList.valueOf(color),limitDrawable,limitDrawable)

}