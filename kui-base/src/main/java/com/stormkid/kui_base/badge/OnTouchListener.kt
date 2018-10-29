package com.stormkid.kui_base.badge

import android.graphics.PointF

/**
badge监听
@author ke_li
@date 2018/10/29
 */
interface OnTouchListener {
    // 重新回到对应的位置
    fun reBack()
    // badge消失的位置
    fun dismiss(pointF: PointF)
}