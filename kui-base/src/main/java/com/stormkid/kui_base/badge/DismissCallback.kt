package com.stormkid.kui_base.badge

import android.view.View

/**
控制取消显示外部监听
@author ke_li
@date 2018/10/30
 */
interface DismissCallback {
    fun dismissed(view: View)
}