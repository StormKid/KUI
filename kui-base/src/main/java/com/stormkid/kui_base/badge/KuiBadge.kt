package com.stormkid.kui_base.badge

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.stormkid.kui_base.InitDrawable
import com.stormkid.kui_base.R
import com.stormkid.kui_base.button.KuiButton
import com.stormkid.kui_base.dimen.DimenUtils
import com.stormkid.kui_base.drawables.BgDrawable

/**
出现在button或者layout上的或单独提示的view
@author ke_li
@date 2018/10/19
 */
class KuiBadge : TextView {
    private var isStroke = false
    private var isDragging = false
    private var bgStyle = 0
    private var bgColor = 0
    private var radius = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        val a = context.obtainStyledAttributes(attributeSet,
                R.styleable.KuiBadge, defAttr, 0)
        isStroke = a.getBoolean(R.styleable.KuiBadge_badge_stroked, false)
        bgStyle = a.getInt(R.styleable.KuiBadge_bg_style, 0)
        isDragging = a.getBoolean(R.styleable.KuiBadge_is_dragging, false)
        bgColor = a.getColor(R.styleable.KuiBadge_badge_bgColor,ContextCompat.getColor(context,R.color.primary))
        radius = a.getFloat(R.styleable.KuiBadge_badge_radius,DimenUtils.dip2px(context,3f).toFloat())

    }

    private fun initBg() {
        val drawableParams = InitDrawable(bgColor, this, radius, isStroke,false)
        gravity = Gravity.CENTER
        this.tag = bgColor
        when(bgStyle){
            KuiButton.radiusType -> BgDrawable.instance.getRadiusDrawable(drawableParams)
            KuiButton.roundType-> BgDrawable.instance.getRoundDrawable(drawableParams)
            KuiButton.circleType->BgDrawable.instance.getCircleDrawable(drawableParams)
        }
    }


    private fun initDrag(){
        if (isDragging)
        BadgeChangeHelper.excute(this,object :DismissCallback{
            override fun dismissed(view: View) {
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initBg()
        initDrag()
    }


    /**
     * 是否镂空
     */
    fun isStroke (isStroke: Boolean){
        this.isStroke = isStroke
    }

    /**
     * 是否滑动消失
     */
    fun isDragging(isDragging:Boolean){
        this.isDragging = isDragging
    }

    /**
     * 设置背景颜色
     */
    fun setBgColor(@ColorInt color: Int){
        this.bgColor=color
    }

    /**
     * 设置圆弧半径
     */
    fun setRadius(radius:Float){
        this.radius = radius
    }
}