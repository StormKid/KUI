package com.stormkid.kui_base.refresh

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
下拉刷新，上拉加载
@author ke_li
@date 2018/10/19
 */
class KuiRefreshLayout : LinearLayout{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        initAttr(context, attributeSet!!, defAttr)
    }

    private fun initAttr(context: Context, attributeSet: AttributeSet, defAttr: Int) {

    }
}