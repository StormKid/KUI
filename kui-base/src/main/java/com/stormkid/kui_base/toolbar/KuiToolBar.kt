package com.stormkid.kui_base.toolbar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
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
class KuiToolBar : RelativeLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        val a = context.obtainStyledAttributes(attributeSet,
                R.styleable.KuiToolBar, defAttr, 0)
        val imageSize = a.getDimensionPixelSize(R.styleable.KuiToolBar_image_dimen, 40).toFloat()
        val leftIcon = a.getResourceId(R.styleable.KuiToolBar_left_image_icon, 0)
        val rightIcon = a.getResourceId(R.styleable.KuiToolBar_right_image_icon, 0)
        val title = a.getString(R.styleable.KuiToolBar_title_text)
        val titleSize = a.getDimensionPixelOffset(R.styleable.KuiToolBar_title_size, 14).toFloat()
        val titleGravity = a.getInt(R.styleable.KuiToolBar_title_gravity, 0)
        val isNav = a.getBoolean(R.styleable.KuiToolBar_is_nav_bar, true)
        val content = a.getString(R.styleable.KuiToolBar_content_text)
        val contentSize = a.getDimensionPixelOffset(R.styleable.KuiToolBar_content_size, 12).toFloat()
        val titleColor  = a.getColor(R.styleable.KuiToolBar_title_color,Color.rgb(102,102,102))
        val contentColor = a.getColor(R.styleable.KuiToolBar_content_color,Color.rgb(153,153,153))
        initImageView(imageSize, leftIcon, rightIcon)
        initTitle(title, titleGravity, imageSize, titleSize,titleColor)
        initContent(content, contentSize,contentColor)
        if (isNav && context is Activity) this.getChildAt(0).setOnClickListener { context.finish() }

    }

    private fun initContent(content: String?, contentSize: Float, contentColor: Int) {
        if (TextUtils.isEmpty(content)) return
        contentView = TextView(context).apply {
            text = content
            setTextSize(TypedValue.COMPLEX_UNIT_SP, contentSize)
            setTextColor(contentColor)
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                addRule(RelativeLayout.CENTER_VERTICAL)
                addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            }
        }
        this.addView(contentView)
    }

    private fun initTitle(title: String?, titleGravity: Int, imageSize: Float, titleSize: Float, titleColor: Int) {
        if (TextUtils.isEmpty(title)) return
        val group = this
        titleView = TextView(context).apply {
            text = title
            setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
            setTextColor(titleColor)
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                addRule(RelativeLayout.CENTER_VERTICAL)
                when (titleGravity) {
                    0 -> addRule(RelativeLayout.CENTER_IN_PARENT)
                    1 -> setMargins(ViewUtils.dip2px(context, imageSize) + 5, 0, 0, 0)
                    2 -> {
                        addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                        setMargins(0, 0, ViewUtils.dip2px(context, imageSize) + 5, 0)
                    }
                }
            }

        }
        group.addView(titleView)
    }

    private var leftIcon:ImageView? = null
    private var rightIcon:ImageView? = null
    private var titleView:TextView? = null
    private var contentView:TextView? = null
    fun setOnToolClickListener(listener: (View)->Unit){
        setOnClickListener(listener)
    }

    fun setLeftIconClickListener(listener: (View)->Unit){
        if (null!=leftIcon)leftIcon?.setOnClickListener(listener)
    }

    fun setRightIconClickListener(listener: (View)->Unit){
        if (null!=rightIcon)rightIcon?.setOnClickListener(listener)
    }

    fun setTitle(text:String){
        if (null!=titleView&&!TextUtils.isEmpty(text))titleView?.text = text
    }

    fun setContent(text:String){
        if (null!=contentView&&!TextUtils.isEmpty(text))contentView?.text = text
    }

    private fun initImageView(imageSize: Float, leftIconRes: Int, rightIconRes: Int) {
        leftIcon = ImageView(context).apply {
            layoutParams = RelativeLayout.LayoutParams(ViewUtils.dip2px(context, imageSize), ViewUtils.dip2px(context, imageSize))
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(leftIconRes)
        }

        rightIcon = ImageView(context).apply {
            layoutParams = RelativeLayout.LayoutParams(ViewUtils.dip2px(context, imageSize), ViewUtils.dip2px(context, imageSize)).apply { addRule(RelativeLayout.ALIGN_PARENT_RIGHT) }
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(rightIconRes)
        }
        this.addView(leftIcon)
        this.addView(rightIcon)
    }


}