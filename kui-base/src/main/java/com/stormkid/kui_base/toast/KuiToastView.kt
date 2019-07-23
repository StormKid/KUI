package com.stormkid.kui_base.toast


import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.stormkid.kui_base.Utils
import com.stormkid.kui_base.button.KuiButton
import com.stormkid.kui_base.dimen.DimenUtils

/**

@author ke_li
@date 2018/12/6
 */
class KuiToastView:RelativeLayout {

    private val kuiview =  KuiButton(context)
    private val marginDimen = DimenUtils.dip2px(context,20f)
    private val statusHeight = Utils.getStatusBarHeight(context) + marginDimen
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr){
        kuiview.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
            addRule( RelativeLayout.CENTER_HORIZONTAL)
            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM )
            topMargin = statusHeight
            leftMargin = marginDimen
            rightMargin = marginDimen
            bottomMargin = marginDimen
        }
        kuiview.setPadding(marginDimen,marginDimen/2,marginDimen,marginDimen/2)
        addView(kuiview)

    }

    fun getToastView() = kuiview.apply {
        val layoutParams = kuiview.layoutParams as RelativeLayout.LayoutParams
        layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM )
        kuiview.layoutParams = layoutParams
    }

    fun getToastViewWithoutRule()= kuiview

}