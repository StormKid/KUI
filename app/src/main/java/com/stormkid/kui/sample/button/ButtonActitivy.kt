package com.stormkid.kui.sample.button

import android.util.Log
import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import kotlinx.android.synthetic.main.button_sample.*

/**
button sample
@author ke_li
@date 2018/9/30
 */
class ButtonActitivy:MainActivity() {
    override fun getLayoutId(): Int = R.layout.button_sample


    override fun initView() {
        click_right.setOnClickListener {
            Log.d("xxx","66666666666")
        }

        do_now.setOnClickListener {
            Log.w("eee","7777")
        }

        do_it.setOnClickListener {
            Log.e("xxx","8888888")
        }
    }
}