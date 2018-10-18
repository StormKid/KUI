package com.stormkid.kui_base.ripple

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
将view添加到此中，获取其点击水波纹效果
@author ke_li
@date 2018/10/18
 */
class RippleLayout : FrameLayout{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr)
}