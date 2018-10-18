package com.stormkid.kui_base.helper

import android.view.View
import com.stormkid.kui_base.ripple.RippleLayout

/**

@author ke_li
@date 2018/10/18
 */
interface ItemClickRule {
    fun setOnClickLitener(lisener:(view:View,layout:RippleLayout)->Unit)
}