package com.stormkid.kui_base

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView

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
        var isStroke:Boolean =false
)