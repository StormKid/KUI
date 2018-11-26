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
        show_down_window.setOnClickListener {
            KuiPop(KuiPop.PopParams(this,R.layout.alert_sample),this).show(show_down_window)
        }

        show_down_window_with_bg.setOnClickListener{
            KuiPop(KuiPop.PopParams(this,R.layout.alert_sample),this).needBg(true).show(show_down_window_with_bg)
        }
        show_right_window.setOnClickListener{
            KuiPop(KuiPop.PopParams(this,R.layout.alert_sample),this).show(show_right_window,KuiPop.RIGHT)
        }

        show_right_window_with_bg.setOnClickListener{
            KuiPop(KuiPop.PopParams(this,R.layout.alert_sample),this).needBg(true).show(show_right_window_with_bg,KuiPop.RIGHT)
        }
    }
}