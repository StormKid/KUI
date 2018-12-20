package com.stormkid.kui.sample.input

import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import com.stormkid.kui_base.input.InputHelper

/**

@author ke_li
@date 2018/10/26
 */
class InputActivity:MainActivity() {
    override fun getLayoutId(): Int = R.layout.input_sample



    override fun initView() {
        InputHelper.instance.initTouchEdit(this){
        }
    }

    override fun onStop() {
        super.onStop()
    }
}