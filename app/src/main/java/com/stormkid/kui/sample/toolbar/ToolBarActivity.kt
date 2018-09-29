package com.stormkid.kui.sample.toolbar

import android.util.Log
import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import kotlinx.android.synthetic.main.toolbar_sample.*

/**
toolbar的例子
@author ke_li
@date 2018/9/29
 */
class ToolBarActivity :MainActivity(){
    override fun getLayoutId(): Int  = R.layout.toolbar_sample

    override fun initView() {
        tool_title.setOnToolClickListener{
            Log.d("xxx","嘿嘿嘿")
        }
    }

}