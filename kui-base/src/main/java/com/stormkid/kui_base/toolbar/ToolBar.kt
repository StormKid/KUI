package com.stormkid.kui_base.toolbar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
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
        val leftIcon = a.getResourceId(R.styleable.ToolBar_left_image_icon, 0)
        val rightIcon = a.getResourceId(R.styleable.ToolBar_right_image_icon, 0)
        val title = a.getString(R.styleable.ToolBar_title_text)
        val titleSize = a.getDimensionPixelOffset(R.styleable.ToolBar_title_size, 14).toFloat()
        val titleGravity = a.getInt(R.styleable.ToolBar_title_gravity, 0)
        val isNav = a.getBoolean(R.styleable.ToolBar_is_nav_bar, true)
        val content = a.getString(R.styleable.ToolBar_content_text)
        val contentSize = a.getDimensionPixelOffset(R.styleable.ToolBar_content_size, 12).toFloat()
        initImageView(imageSize, leftIcon, rightIcon)
        initTitle(title, titleGravity, imageSize, titleSize)
        initContent(content, contentSize)
        if (isNav && context is Activity) this.getChildAt(0).setOnClickListener { context.finish() }

    }

    private fun initContent(content: String?, contentSize: Float) {
        if (TextUtils.isEmpty(content)) return
        contentView = TextView(context).apply {
            text = content
            setTextSize(TypedValue.COMPLEX_UNIT_SP, contentSize)
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                addRule(RelativeLayout.CENTER_VERTICAL)
                addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            }
        }
        this.addView(contentView)
    }

    private fun initTitle(title: String?, titleGravity: Int, imageSize: Float, titleSize: Float) {
        if (TextUtils.isEmpty(title)) return
        val group = this
        titleView = TextView(context).apply {
            text = title
            setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
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
    fun setOnToolClickListener(listener: OnClickListener){
        this.setOnClickListener(listener)
    }

    fun setLeftIconClickListener(listener: OnClickListener){
        if (null!=leftIcon)leftIcon?.setOnClickListener(listener)
    }

    fun setRightIconClickListener(listener: OnClickListener){
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