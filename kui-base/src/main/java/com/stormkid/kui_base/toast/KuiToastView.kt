package com.stormkid.kui_base.toast

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.RelativeLayout
import com.stormkid.kui_base.InitDrawable
import com.stormkid.kui_base.dimen.DimenUtils
import com.stormkid.kui_base.drawables.BgDrawable
import com.stormkid.kui_base.toolbar.KuiToolBar

/**

@author ke_li
@date 2018/12/6
 */
class KuiToastView:RelativeLayout {

    private val kuiview =  KuiToolBar(context)
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr){
        kuiview.layoutParams = ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
            height = DimenUtils.dip2px(context,40f)
        }
        BgDrawable.instance.getRoundDrawable(InitDrawable(Color.LTGRAY,kuiview,0f))
        kuiview.startAnimation(TranslateAnimation(0f,0f,-100f,100f).apply { duration = 2000 })
        addView(kuiview)

    }
}