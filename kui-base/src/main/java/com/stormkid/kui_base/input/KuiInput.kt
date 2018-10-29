package com.stormkid.kui_base.input

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import com.stormkid.kui_base.dimen.DimenUtils

/**
input 文字
@author ke_li
@date 2018/10/19
 */
class KuiInput : LinearLayout {


    private val paddingDimen = DimenUtils.dip2px(context,10f)
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        initEditText()
    }

    private fun initEditText() {
        KuiEditText(context).apply {
            setBackgroundColor(Color.TRANSPARENT)
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                weight = 1f
                setPadding(paddingDimen,paddingDimen,paddingDimen,paddingDimen)
            }
            this@KuiInput.addView(this)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


}