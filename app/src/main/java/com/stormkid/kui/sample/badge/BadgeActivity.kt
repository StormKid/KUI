package com.stormkid.kui.sample.badge

import android.view.View
import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import com.stormkid.kui_base.badge.BadgeChangeHelper
import com.stormkid.kui_base.badge.DismissCallback
import kotlinx.android.synthetic.main.badge_sample.*

/**

@author ke_li
@date 2018/10/30
 */
class BadgeActivity:MainActivity() {
    override fun getLayoutId(): Int  = R.layout.badge_sample


    override fun initView() {
        BadgeChangeHelper.excute(search_badge,object :DismissCallback{
            override fun dismissed(view: View) {
            }

        })
    }
}