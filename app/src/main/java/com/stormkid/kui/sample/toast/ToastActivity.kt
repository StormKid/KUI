package com.stormkid.kui.sample.toast

import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import com.stormkid.kui_base.toast.KuiToast
import kotlinx.android.synthetic.main.sample_toast.*

/**

@author ke_li
@date 2018/12/4
 */
class ToastActivity : MainActivity() {
    override fun getLayoutId(): Int = R.layout.sample_toast

    override fun initView() {
        show_toast.setOnClickListener {
            KuiToast(this).showToast()
        }
    }
}