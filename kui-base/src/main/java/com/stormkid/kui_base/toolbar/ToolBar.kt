package com.stormkid.kui_base.toolbar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.stormkid.kui_base.R
import com.stormkid.kui_base.dimen.ViewUtils

/**
主代码
@author ke_li
@date 2018/9/25
 */
class ToolBar : RelativeLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        val a = context.obtainStyledAttributes(attributeSet,
                R.styleable.ToolBar, defAttr, 0)
        val imageSize = a.getDimensionPixelSize(R.styleable.ToolBar_image_dimen, 40).toFloat()
        val leftIcon = a.getResourceId(R.styleable.ToolBar_left_image_icon,0)
        val rightIcon = a.getResourceId(R.styleable.ToolBar_right_image_icon,0)
        val title = a.getString(R.styleable.ToolBar_title_text)
        val titleGravity = a.getInt(R.styleable.ToolBar_title_gravity,0)
        val isNav = a.getBoolean(R.styleable.ToolBar_is_nav_bar,true)
        initImageView(imageSize,leftIcon,rightIcon)
        initTitle(title,titleGravity,imageSize)
        if (isNav&&context is Activity) this.getChildAt(0).setOnClickListener { context.finish() }

    }

    private fun initTitle(title: String, titleGravity: Int, imageSize: Float) {
        val group = this
        val titleView = TextView(context).apply {
            text = title
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                addRule(RelativeLayout.CENTER_VERTICAL)
                when(titleGravity){
                     0-> addRule(RelativeLayout.CENTER_IN_PARENT)
                     1-> setMargins(ViewUtils.dip2px(context,imageSize)+5,0,0,0)
                     2-> setMargins(0,0,ViewUtils.dip2px(context,imageSize)+5,0)
                 }
            }

        }
        group.addView(titleView)
    }

    private fun initImageView(imageSize: Float, leftIcon: Int, rightIcon: Int) {
        val leftImageView = ImageView(context).apply {
            layoutParams = RelativeLayout.LayoutParams(ViewUtils.dip2px(context, imageSize), ViewUtils.dip2px(context, imageSize))
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(leftIcon)
        }

        val rightImageView = ImageView(context).apply {
            layoutParams = RelativeLayout.LayoutParams(ViewUtils.dip2px(context, imageSize), ViewUtils.dip2px(context, imageSize)).apply { addRule(RelativeLayout.ALIGN_PARENT_RIGHT) }
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(rightIcon)
        }
        this.addView(leftImageView)
        this.addView(rightImageView)
    }


}