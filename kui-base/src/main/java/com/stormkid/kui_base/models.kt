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

data class AlphaAnimateModel(val from:Float, val to:Float, override val duration: Long, override val view: View):AnimateValue

data class RotateAnimationModel(val fromDegree:Float, val toDegree:Float, override val duration: Long, override val view: View):AnimateValue

data class TransLateOrScaleAnimationModel(val fromX:Float, val fromY:Float, val toX:Float, val toY:Float, override val duration: Long, override val view: View):AnimateValue

data class DialogModel(@LayoutRes val layoutId:Int,@StyleRes val resId:Int)