package com.stormkid.kui_base

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.annotation.*
import com.stormkid.kui_base.helper.AnimateValue

/**
各种utils需要的model
@author ke_li
@date 2018/9/29
 */

/**
 * svg 图片需要构建的对象
 */
data class InitImgRes(
        @DrawableRes val imgRes: Int,
        @ColorRes val colorRes: Int,
        val imageView: ImageView,
        val context: Context
)

/**
 *  各种View 显示背景需要init构建的对象
 */
data class InitDrawable(
        @ColorInt val colorRes: Int,
        val view: View,
        val radius:Float,
        var isStroke:Boolean =false,
        var showRipple:Boolean = false,
        @ColorInt var rippleColor:Int=Color.argb(120,0,0,0)
)

/**
 *  渐变动画构建对象
 */
data class AlphaAnimateModel(val from:Float, val to:Float, override val duration: Long, override val view: View):AnimateValue

/**
 * 翻转动画构建对象
 */
data class RotateAnimationModel(val fromDegree:Float, val toDegree:Float, override val duration: Long, override val view: View):AnimateValue

/**
 * 位移动画和放大缩小动画
 */
data class TransLateOrScaleAnimationModel(val fromX:Float, val fromY:Float, val toX:Float, val toY:Float, override val duration: Long, override val view: View):AnimateValue
/**
 * 自定义alert 构建对象
 * @param  layoutId 自定alert填充布局
 * @param  styleId 自定义alert xml属性样式
 */
data class DialogModel(@LayoutRes val layoutId:Int,@StyleRes val styleId:Int)

/**
 * 建立自定义button颜色值
 * @param textColor 文字颜色
 * @param bgColor 背景或线条颜色
 * @param rippleColor 点击水波纹颜色
 * @param isStroke 是否是镂空
 */
data class ColorResButton(@ColorRes var textColor:Int = android.R.color.white,@ColorInt var  bgColor:Int = Color.rgb(33,150,243),@ColorInt var rippleColor: Int = Color.BLACK,var isStroke: Boolean = false)