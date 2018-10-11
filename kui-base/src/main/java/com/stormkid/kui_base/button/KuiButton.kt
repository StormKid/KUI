package com.stormkid.kui_base.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.stormkid.kui_base.InitImgRes
import com.stormkid.kui_base.R
import com.stormkid.kui_base.Utils
import com.stormkid.kui_base.dimen.DimenUtils

/**
自定义可带图片的button
@author ke_li
@date 2018/9/28
 */
class KuiButton: LinearLayout {
    private val paddingSize = DimenUtils.dip2px(context,5f)
    // 是否需要显示icon
    private var isShowIcon = false
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        val a = context.obtainStyledAttributes(attributeSet,
                R.styleable.KuiButton, defAttr, 0)

        val iconDimen = a.getDimension(R.styleable.KuiButton_icon_dimen,0f)
        val iconColor = a.getResourceId(R.styleable.KuiButton_icon_color,0)
        val iconRes = a.getResourceId(R.styleable.KuiButton_icon_res,0)
        val textSizeResult = a.getDimensionPixelOffset(R.styleable.KuiButton_text_dimen,26).toFloat()
        val gravity = a.getInt(R.styleable.KuiButton_icon_gravity,0)
        val text = a.getString(R.styleable.KuiButton_text)
        val bg = a.getInt(R.styleable.KuiButton_bg_drawable,0)

        val textView = TextView(context).apply {
            setText(text)
            textSize = DimenUtils.px2sp(context,textSizeResult).toFloat()
            layoutParams =LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        }

        if (iconRes==0) return
        val imageView = ImageView(context).apply {
            setImageResource(iconRes)
            Utils.initSvgColor(InitImgRes(iconRes,iconColor,this,context))
            layoutParams=LinearLayout.LayoutParams(DimenUtils.dip2px(context, iconDimen),DimenUtils.dip2px(context, iconDimen))
        }

        initGravity(gravity,textView,imageView)

    }

    private fun initGravity(gravity: Int, textView: TextView, imageView: ImageView) {
        when(gravity){
            0->{
                orientation = VERTICAL
                setGravity(Gravity.CENTER_VERTICAL)
                addView(imageView)
                textView.setPadding(0,paddingSize,0,0)
                addView(textView)
            }
            1->{
                orientation = HORIZONTAL
                setGravity(Gravity.CENTER_HORIZONTAL)
                addView(imageView)
                textView.setPadding(paddingSize,0,0,0)
                addView(textView)
            }
            2->{
                orientation = HORIZONTAL
                setGravity(Gravity.CENTER_HORIZONTAL)
                textView.setPadding(0,0,paddingSize,0)
                addView(textView)
                addView(imageView)
            }
            3->{
                orientation = VERTICAL
                setGravity(Gravity.CENTER_VERTICAL)
                textView.setPadding(0,0,0,paddingSize)
                addView(textView)
                addView(imageView)
            }
        }
    }


}