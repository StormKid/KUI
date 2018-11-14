package com.stormkid.kui.sample.input

import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import com.stormkid.kui_base.input.InputHelper
import kotlinx.android.synthetic.main.input_sample.*

/**

@author ke_li
@date 2018/10/26
 */
class InputActivity:MainActivity() {
    override fun getLayoutId(): Int = R.layout.input_sample



    override fun initView() {
        InputHelper.instance.initTouchEdit(this){
            input.cleanFocus()
        }
    }

    override fun onStop() {
        super.onStop()
        input.cleanFocus()
    }
}