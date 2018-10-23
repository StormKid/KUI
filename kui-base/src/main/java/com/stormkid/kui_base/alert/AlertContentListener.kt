package com.stormkid.kui_base.alert

import android.view.View
import androidx.fragment.app.DialogFragment

/**

@author ke_li
@date 2018/10/23
 */
interface AlertContentListener {
    fun contentViewEvents(view: View,dialog: DialogFragment)
}