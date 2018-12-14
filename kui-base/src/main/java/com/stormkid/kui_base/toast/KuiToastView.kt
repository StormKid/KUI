package com.stormkid.kui_base.toast

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.stormkid.kui_base.button.KuiButton
import com.stormkid.kui_base.dimen.DimenUtils

/**

@author ke_li
@date 2018/12/6
 */
class KuiToastView:RelativeLayout {

    private val kuiview =  KuiButton(context)
    private val marginDimen = DimenUtils.dip2px(context,20f)
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr){
        kuiview.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
            height = DimenUtils.dip2px(context,40f)
            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            leftMargin = marginDimen
            rightMargin = marginDimen
            bottomMargin = marginDimen

        }
        addView(kuiview)

    }

    fun getToastView() = kuiview.apply {
        val layoutParams = kuiview.layoutParams as RelativeLayout.LayoutParams
        val beforeRule = layoutParams.rules
        if (beforeRule.isNotEmpty()) beforeRule.forEach { layoutParams.removeRule(it)}
    }

    fun getToastViewWithoutRule()= kuiview

}