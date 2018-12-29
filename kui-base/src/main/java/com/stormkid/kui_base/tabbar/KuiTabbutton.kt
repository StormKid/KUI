package com.stormkid.kui_base.tabbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.stormkid.kui_base.R
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
        setIconResColor(R.drawable.ic_home_demo,R.color.primary)
        setIconGravity(KuiButton.iconBottom)
        setTextColor(R.color.primary)
        setText("首页")
        setBgColor(Color.WHITE)
        setRadius(0f)
    } // tab_button 主体
    private val bage = KuiBadge(context).apply {
        layoutParams = RelativeLayout.LayoutParams(40,40).apply {
            addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            setMargins(0,marginDimen,marginDimen,0)
        }
        setBgType(KuiButton.circleType)
        setTextColor(Color.WHITE)
        setDragDismissCallback {  }
        text = "66"
    } // tab_bage 主体
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr){
        addView(button)
        addView(bage)
    }
}