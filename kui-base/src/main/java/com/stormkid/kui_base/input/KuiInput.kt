package com.stormkid.kui_base.input

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.RelativeLayout
import com.stormkid.kui_base.dimen.DimenUtils



/**
input 文字
@author ke_li
@date 2018/10/19
 */
class KuiInput : RelativeLayout {


    private val paddingDimen = DimenUtils.dip2px(context, 10f)
    private val editText = KuiEditText(context).apply {
        setBackgroundColor(Color.WHITE)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setPadding(paddingDimen, paddingDimen, paddingDimen, paddingDimen)
        }
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        initEditText()
    }

    private fun initEditText() {
        addView(editText)
        gravity = Gravity.CENTER_VERTICAL
    }

    fun cleanFocus() {
        editText.loseFocusable()
    }

}