package com.stormkid.kui_base.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.stormkid.kui_base.R
import com.stormkid.kui_base.dimen.DimenUtils

/**
自定义可带图片的button
@author ke_li
@date 2018/9/28
 */
class KuiButton : LinearLayout {
    private val paddingSize = DimenUtils.dip2px(context,5f)
    // 是否需要显示icon
    private var isShowIcon = false
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        val a = context.obtainStyledAttributes(attributeSet,
                R.styleable.KuiButton, defAttr, 0)

        val iconDimen = a.getDimension(R.styleable.KuiButton_icon_dimen,0f)
        val textSize = a.getDimension(R.styleable.KuiButton_text_dimen,13f)
        val gravity = a.getInt(R.styleable.KuiButton_icon_gravity,0)
        val iconRes = a.getResourceId(R.styleable.KuiButton_icon_res,0)
        val text = a.getString(R.styleable.KuiButton_text)

    }


}