package com.stormkid.kui_base

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.widget.ImageView

/**
各种utils需要的model
@author ke_li
@date 2018/9/29
 */

data class InitImgRes(
        @DrawableRes val imgRes: Int,
        @ColorRes val colorRes: Int,
        val imageView: ImageView,
        val context: Context
)