package com.stormkid.kui_base.tabbar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.stormkid.kui_base.badge.KuiBadge
import com.stormkid.kui_base.button.KuiButton
import com.stormkid.kui_base.dimen.DimenUtils

/**

@author ke_li
@date 2018/12/25
 */
class KuiTabbutton : RelativeLayout{
    private val marginDimen = DimenUtils.dip2px(context,5f)
    private val button = KuiButton(context).apply {
        layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
    } // tab_button 主体
    private val bage = KuiBadge(context).apply {
        layoutParams = RelativeLayout.LayoutParams(40,40).apply {
            addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            setMargins(0,marginDimen,marginDimen,0)
        }
    } // tab_bage 主体
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr){
        addView(button)
        addView(bage)
    }

    fun getButton() = button
    fun getBage() = bage
}