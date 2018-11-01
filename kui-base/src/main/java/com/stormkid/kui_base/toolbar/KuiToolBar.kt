package com.stormkid.kui_base.toolbar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.stormkid.kui_base.InitImgRes
import com.stormkid.kui_base.R
import com.stormkid.kui_base.Utils
import com.stormkid.kui_base.dimen.DimenUtils
import com.stormkid.kui_base.drawables.RippleDrawable

/**
自定义带点击功能的banner
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
        val imageSize = a.getDimension(R.styleable.KuiToolBar_image_dimen, 40f)
        val leftIcon = a.getResourceId(R.styleable.KuiToolBar_left_image_icon, 0)
        val rightIcon = a.getResourceId(R.styleable.KuiToolBar_right_image_icon, 0)
        val title = a.getString(R.styleable.KuiToolBar_title_text)
        val titleSize = a.getDimension(R.styleable.KuiToolBar_title_size,  DimenUtils.sp2px(context,14f).toFloat())
        val titleGravity = a.getInt(R.styleable.KuiToolBar_title_gravity, 0)
        val isNav = a.getBoolean(R.styleable.KuiToolBar_is_nav_bar, true)
        val content = a.getString(R.styleable.KuiToolBar_content_text)
        val contentSize = a.getDimension(R.styleable.KuiToolBar_content_size, DimenUtils.sp2px(context,12f).toFloat())
        val titleColor = a.getColor(R.styleable.KuiToolBar_title_color, Color.rgb(102, 102, 102))
        val contentColor = a.getColor(R.styleable.KuiToolBar_content_color, Color.rgb(153, 153, 153))
        val leftIconColor = a.getResourceId(R.styleable.KuiToolBar_left_icon_color, 0)
        val rightIconColor = a.getResourceId(R.styleable.KuiToolBar_right_icon_color, 0)
        val isRightText = a.getBoolean(R.styleable.KuiToolBar_is_right_text, false)
        val rippleColor = a.getColor(R.styleable.KuiToolBar_toolbar_ripple_color,Color.argb(120,0,0,0))
        initImageView(imageSize, leftIcon, rightIcon, leftIconColor, rightIconColor)
        initTitle(title, titleGravity, imageSize, titleSize, titleColor)
        initContent(content, contentSize, contentColor, imageSize, isRightText)
        if (isNav && context is Activity) this.getChildAt(0).setOnClickListener { context.finish() }
        else  background = RippleDrawable(rippleColor,background)
    }

    private fun initContent(content: String?, contentSize: Float, contentColor: Int, imageSize: Float, rightText: Boolean) {
        if (TextUtils.isEmpty(content)) return
        contentView = TextView(context).apply {
            text = content
            Log.w("float","----$contentSize---")
            setTextSize( TypedValue.COMPLEX_UNIT_PX,contentSize)
            setTextColor(contentColor)
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                addRule(RelativeLayout.CENTER_VERTICAL)
                addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                if (rightText)
                    setMargins(0, 0, DimenUtils.dip2px(context, 20f) , 0)
                else
                    setMargins(0, 0, DimenUtils.dip2px(context, imageSize) + 5, 0)
            }
        }
        this.addView(contentView)
    }

    private fun initTitle(title: String?, titleGravity: Int, imageSize: Float, titleSize: Float, titleColor: Int) {
        if (TextUtils.isEmpty(title)) return
        val group = this
        titleView = TextView(context).apply {
            text = title
            setTextSize( TypedValue.COMPLEX_UNIT_PX,titleSize)
            setTextColor(titleColor)
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                addRule(RelativeLayout.CENTER_VERTICAL)
                when (titleGravity) {
                    0 -> addRule(RelativeLayout.CENTER_IN_PARENT)
                    1 -> setMargins(DimenUtils.dip2px(context, imageSize) + 5, 0, 0, 0)
                    2 -> {
                        addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                        setMargins(0, 0, DimenUtils.dip2px(context, imageSize) + 5, 0)
                    }
                }
            }

        }
        group.addView(titleView)
    }

    private var leftIcon: ImageView? = null
    private var rightIcon: ImageView? = null
    private var titleView: TextView? = null
    private var contentView: TextView? = null
    /**
     * 整个条目点击方法
     */
    fun setOnToolClickListener(listener: (View) -> Unit) {
        setOnClickListener(listener)
    }

    /**
     * 左侧图标点击事件
     */
    fun setLeftIconClickListener(listener: (View) -> Unit) {
        if (null != leftIcon) leftIcon?.setOnClickListener(listener)
    }

    /**
     * 右侧图标点击事件
     */
    fun setRightIconClickListener(listener: (View) -> Unit) {
        rightIcon?.setOnClickListener(listener)
    }

    /**
     * 设置主标题文字
     */
    fun setTitle(text: String) {
        if (null != titleView && !TextUtils.isEmpty(text)) titleView?.text = text
    }

    /**
     * 设置小标题文字
     */
    fun setContent(text: String) {
        if (null != contentView && !TextUtils.isEmpty(text)) contentView?.text = text
    }

    /**
     * 设置小标题文字点击事件
     */
    fun setRightTextClick(listener: (View) -> Unit){
        contentView?.setOnClickListener (listener )
    }

    /**
     * 设置左图标
     */
    fun setLeftIcon(@DrawableRes icon:Int){
         leftIcon?.setImageResource(icon)
    }

    /**
     * 设置右图标
     */
    fun setRightIcon(@DrawableRes icon: Int){
         rightIcon?.setImageResource(icon)
    }

    /**
     * 设置左边图标和颜色 SVG专用
     */
    fun setLeftIconResColor(@DrawableRes res:Int ,@ColorRes color:Int){
        if (null!=leftIcon) Utils.initSvgColor(InitImgRes(res,color,leftIcon!!,context))
    }

    /**
     * 设置右侧图标和颜色 SVG专用
     */
    fun  setRightIconResColor(@DrawableRes res:Int,@ColorRes color: Int){
        if (null!=rightIcon) Utils.initSvgColor(InitImgRes(res,color,rightIcon!!,context))
    }

    /**
     * 设置标题颜色
     */
    fun  setTitleColor(@ColorRes color: Int){
         titleView?.setTextColor(ContextCompat.getColor(context,color))
    }

    /**
     * 设置副标题颜色
     */
    fun  setContentColor(@ColorRes color: Int){
         contentView?.setTextColor(ContextCompat.getColor(context,color))
    }

    /**
     * 修改标题字号
     */
    @Deprecated("尽量不要在代码中改变字号")
    fun setTitleSize(  size:Float){
        titleView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    /**
     * 修改副标题字号
     */
    @Deprecated("尽量不要在代码中改变字号")
    fun setContentSize(  size:Float){
        contentView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    /**
     * 修改水波纹颜色
     */
    fun setRippleColor(@ColorInt color: Int){
        background = RippleDrawable(color,background)
    }

    private fun initImageView(imageSize: Float, leftIconRes: Int, rightIconRes: Int, leftIconColor: Int, rightIconColor: Int) {
        leftIcon = ImageView(context).apply {
            layoutParams = RelativeLayout.LayoutParams(DimenUtils.dip2px(context, imageSize), DimenUtils.dip2px(context, imageSize)).apply { addRule(RelativeLayout.CENTER_VERTICAL) }
            scaleType = ImageView.ScaleType.FIT_XY
            setPadding(16,16,16,16)
            if (leftIconColor != 0)
                Utils.initSvgColor(InitImgRes(leftIconRes, leftIconColor, this, context))
            else setImageResource(leftIconRes)
        }

        rightIcon = ImageView(context).apply {
            layoutParams = RelativeLayout.LayoutParams(DimenUtils.dip2px(context, imageSize), DimenUtils.dip2px(context, imageSize)).apply {
                addRule(RelativeLayout.CENTER_VERTICAL)
                addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            }
            setPadding(16,16,16,16)
            scaleType = ImageView.ScaleType.FIT_XY
            if (rightIconColor != 0)
                Utils.initSvgColor(InitImgRes(rightIconRes, rightIconColor, this, context))
            else setImageResource(rightIconRes)
        }
        this.addView(leftIcon)
        this.addView(rightIcon)
    }

}