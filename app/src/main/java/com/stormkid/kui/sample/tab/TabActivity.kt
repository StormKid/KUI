package com.stormkid.kui.sample.tab

import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import kotlinx.android.synthetic.main.sample_tab.*

/**

@author ke_li
@date 2019/1/4
 */
class TabActivity : MainActivity() {
    override fun getLayoutId(): Int= R.layout.sample_tab

    override fun initView() {
        for (i in 1 ..10){
            tab.addTab(object : TabBean{
                override val position: Int
                    get() = i
                override var title: String
                    get() = "tab$i"
                    set(value) {}
            })
        }
    }
}