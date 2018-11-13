package com.stormkid.kui_base.input

import android.app.Activity
import android.view.MotionEvent
import android.view.View

/**

@author ke_li
@date 2018/11/13
 */
class InputHelper private constructor() {

    companion object {
        val instance by lazy { InputHelper() }
    }

    fun initTouchEdit(activity: Activity, function: () -> Unit) {
        activity.window.decorView.setOnTouchListener { v, event ->
            val view = activity.currentFocus?.let {
                if (it is KuiEditText) function()
                it
            }?:function()
            false
        }
    }


    fun initPoint(v: View, event: MotionEvent): Boolean {
        if (v is KuiEditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false
    }

}