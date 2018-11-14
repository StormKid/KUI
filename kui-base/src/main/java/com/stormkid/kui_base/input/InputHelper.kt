package com.stormkid.kui_base.input

import android.app.Activity
import com.stormkid.kui_base.Log

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
                Log.w(it.javaClass.name)
                if (it is KuiEditText) function()
                it
            }
            false
        }
    }
}