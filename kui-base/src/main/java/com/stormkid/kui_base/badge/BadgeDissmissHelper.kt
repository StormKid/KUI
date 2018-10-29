package com.stormkid.kui_base.badge

import android.content.Context
import android.view.MotionEvent
import android.view.View

/**

@author ke_li
@date 2018/10/29
 */
class BadgeDissmissHelper(private val context: Context,private val onTouchListener: OnTouchListener): View.OnTouchListener {
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{}
            MotionEvent.ACTION_MOVE->{}
            MotionEvent.ACTION_UP->{}
        }
        return true
    }
}