package com.stormkid.kui_base.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.stormkid.kui_base.InitDrawable
import com.stormkid.kui_base.InitImgRes
import com.stormkid.kui_base.R
import com.stormkid.kui_base.Utils
import com.stormkid.kui_base.dimen.DimenUtils
import com.stormkid.kui_base.drawables.BgDrawable

/**
自定义可带图片的button
@author ke_li
@date 2018/9/28
 */
class KuiButton : LinearLayout {

    companion object {
        const val radiusType = 0
        const val circleType = 1
        const val roundType = 2
    }

    private val paddingSize = DimenUtils.dip2px(context, 10f)
    private var bg = 0
    @ColorInt
    private var bgColor = 0
    private var ignorePadding = false
    private var textView: TextView? = null
    private var icon: ImageView? = null
    private var radius = 0f
    private var isStroke = false
    private var isShowRipple = false
    private var rippleColor = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        val a = context.obtainStyledAttributes(attributeSet,
                R.styleable.KuiButton, defAttr, 0)
        radius = a.getDimension(R.styleable.KuiButton_bg_radius, DimenUtils.dip2px(context, 3f).toFloat())
        val iconDimen = a.getDimension(R.styleable.KuiButton_icon_dimen, DimenUtils.dip2px(context, 28f).toFloat())
        val iconColor = a.getResourceId(R.styleable.KuiButton_icon_color, 0)
        val iconRes = a.getResourceId(R.styleable.KuiButton_icon_res, 0)
        val textSizeResult = a.getDimension(R.styleable.KuiButton_text_dimen, 26f)
        val gravity = a.getInt(R.styleable.KuiButton_icon_gravity, 0)
        val text = a.getString(R.styleable.KuiButton_text)
        val textColor = a.getColor(R.styleable.KuiButton_text_color, Color.rgb(255, 255, 255))
        bg = a.getInt(R.styleable.KuiButton_bg_drawable, 0)
        bgColor = a.getColor(R.styleable.KuiButton_bg_color, Color.rgb(33, 150, 243))
        ignorePadding = a.getBoolean(R.styleable.KuiButton_ignore_padding, false)
        isStroke = a.getBoolean(R.styleable.KuiButton_is_stroke, false)
        rippleColor = a.getColor(R.styleable.KuiButton_button_ripple_color, 0)
        isShowRipple = a.getBoolean(R.styleable.KuiButton_is_show_ripple, true)
        textView = TextView(context).apply {
            setText(text)
            setTextColor(textColor)
            textSize = DimenUtils.px2sp(context, textSizeResult).toFloat()
            layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        setGravity(Gravity.CENTER)
        if (iconRes == 0) {
            addView(textView)
            return
        }
        icon = ImageView(context).apply {
            scaleType = ImageView.ScaleType.FIT_XY
            layoutParams = LinearLayout.LayoutParams(iconDimen.toInt(), iconDimen.toInt())
            setPadding(5, 5, 5, 5)
            if (iconColor == 0)
                setImageResource(iconRes)
            else
                Utils.initSvgColor(InitImgRes(iconRes, iconColor, this, context))
        }

        initGravity(gravity, textView!!, icon!!)
    }

    /**
     * 设置button文字
     */
    fun setText(text: String) {
        if (null != textView) textView?.text = text
    }

    /**
     * 设置图标资源
     */
    fun setIconRes(@DrawableRes imgRes: Int) {
        if (null != icon) icon?.setImageResource(imgRes)
    }

    /**
     * 设置图标大小
     */
    fun setIconDimen(dimen: Float) {
        val result = DimenUtils.dip2px(context, dimen)
        if (null != icon) icon?.layoutParams = LinearLayout.LayoutParams(result, result)
    }


    /**
     * svg专用 给其上颜色
     */
    fun setIconResColor(@DrawableRes src: Int, @ColorRes res: Int) {
        if (null != icon) Utils.initSvgColor(InitImgRes(src, res, icon!!, context))
    }

    /**
     * 设置button倒角
     */
    fun setRadius(dimen: Float) {
        this.radius = dimen
    }


    @Deprecated("不建议代码更改字号")
    fun setTextSize(size: Float) {
        if (null != textView) textView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }


    fun setTextColor(@ColorRes res: Int) {
        val color = ContextCompat.getColor(context, res)
        if (null != textView) textView?.setTextColor(color)
    }

    /**
     * 设置背景类型
     */
    fun setBgType(bg: Int) {
        this.bg = bg
    }

    /**
     * 设置button背景色
     */
    fun setBgColor(@ColorInt bgColor: Int) {
        this.bgColor = bgColor
    }

    /**
     * 设置水波纹颜色
     */
    fun setRippleColor(@ColorInt rippleColor:Int){
        this.rippleColor = rippleColor
    }

    private fun initBg() {
        var drawableParams = InitDrawable(bgColor, this, radius, isStroke, isShowRipple)
        if (rippleColor != 0)
            drawableParams = InitDrawable(bgColor, this, radius, isStroke, isShowRipple, rippleColor)
        when (bg) {
            radiusType -> BgDrawable.instance.getRadiusDrawable(drawableParams)
            circleType -> BgDrawable.instance.getCircleDrawable(drawableParams)
            roundType -> BgDrawable.instance.getRoundDrawable(drawableParams)

        }
        return
    }

    private fun initGravity(gravity: Int, textView: TextView, icon: ImageView) {
        if (!ignorePadding)
            setPadding(paddingSize, paddingSize, paddingSize, paddingSize)
        when (gravity) {
            0 -> {
                orientation = VERTICAL
                addView(icon)
                addView(textView)
            }
            1 -> {
                orientation = HORIZONTAL
                addView(icon)
                addView(textView)
            }
            2 -> {
                orientation = HORIZONTAL
                addView(textView)
                addView(icon)
            }
            3 -> {
                orientation = VERTICAL
                addView(textView)
                addView(icon)
            }
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initBg()
    }

}