package com.stormkid.kui.sample.pop

import android.view.View
import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import com.stormkid.kui_base.pop.KuiPop
import com.stormkid.kui_base.pop.PopwindowListener
import kotlinx.android.synthetic.main.pop_sample.*

/**

@author ke_li
@date 2018/11/15
 */
class PopActivity:MainActivity(),PopwindowListener {
    override fun TouchView(view: View) {
    }


    override fun getLayoutId(): Int = R.layout.pop_sample



    override fun initView() {
        show_pop_window.setOnClickListener {
            KuiPop(KuiPop.PopParams(this,R.layout.alert_sample),this).show(show_pop_window, KuiPop.TOP)

        }


    }
}