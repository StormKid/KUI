package com.stormkid.kui_base.tabbar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

/**
任意位置的tab栏目
@author ke_li
@date 2018/10/22
 */
class KuiTabbar : HorizontalScrollView {



    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr)


}