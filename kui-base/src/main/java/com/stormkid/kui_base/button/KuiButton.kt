package com.stormkid.kui_base.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
    private val paddingSize = DimenUtils.dip2px(context, 5f)
    private var bg = 0
    private var bgColor = 0
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        val a = context.obtainStyledAttributes(attributeSet,
                R.styleable.KuiButton, defAttr, 0)

        val iconDimen = a.getDimension(R.styleable.KuiButton_icon_dimen, 0f)
        val iconColor = a.getResourceId(R.styleable.KuiButton_icon_color, 0)
        val iconRes = a.getResourceId(R.styleable.KuiButton_icon_res, 0)
        val textSizeResult = a.getDimensionPixelOffset(R.styleable.KuiButton_text_dimen, 26).toFloat()
        val gravity = a.getInt(R.styleable.KuiButton_icon_gravity, 0)
        val text = a.getString(R.styleable.KuiButton_text)
        val textColor = a.getColor(R.styleable.KuiButton_text_color, Color.rgb(255, 255, 255))
        bg = a.getInt(R.styleable.KuiButton_bg_drawable, 0)
        bgColor = a.getColor(R.styleable.KuiButton_bg_color, Color.rgb(33, 150, 243))

        val textView = TextView(context).apply {
            setText(text)
            setTextColor(textColor)
            textSize = DimenUtils.px2sp(context, textSizeResult).toFloat()
            layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        if (iconRes == 0) {
            setGravity(Gravity.CENTER)
            addView(textView)
            return
        }
        val imageView = ImageView(context).apply {
            setImageResource(iconRes)
            Utils.initSvgColor(InitImgRes(iconRes, iconColor, this, context))
            layoutParams = LinearLayout.LayoutParams(DimenUtils.dip2px(context, iconDimen), DimenUtils.dip2px(context, iconDimen))
        }

        initGravity(gravity, textView, imageView)
    }

    private fun initBg(bg: Int, bgColor: Int) {
        val drawableParams = InitDrawable(bgColor, this)
        when (bg) {
            0 -> BgDrawable.instance.getRadiusDrawable(drawableParams)
            1 -> BgDrawable.instance.getCircleDrawable(drawableParams)
            2 -> BgDrawable.instance.getRoundDrawable(drawableParams)

        }
        return
    }

    private fun initGravity(gravity: Int, textView: TextView, imageView: ImageView) {
        when (gravity) {
            0 -> {
                orientation = VERTICAL
                setGravity(Gravity.CENTER_VERTICAL)
                addView(imageView)
                textView.setPadding(0, paddingSize, 0, 0)
                addView(textView)
            }
            1 -> {
                orientation = HORIZONTAL
                setGravity(Gravity.CENTER_HORIZONTAL)
                addView(imageView)
                textView.setPadding(paddingSize, 0, 0, 0)
                addView(textView)
            }
            2 -> {
                orientation = HORIZONTAL
                setGravity(Gravity.CENTER_HORIZONTAL)
                textView.setPadding(0, 0, paddingSize, 0)
                addView(textView)
                addView(imageView)
            }
            3 -> {
                orientation = VERTICAL
                setGravity(Gravity.CENTER_VERTICAL)
                textView.setPadding(0, 0, 0, paddingSize)
                addView(textView)
                addView(imageView)
            }
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initBg(bg, bgColor)
    }

}