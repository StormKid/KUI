package com.stormkid.kui.sample.badge

import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import kotlinx.android.synthetic.main.badge_sample.*

/**

@author ke_li
@date 2018/10/30
 */
class BadgeActivity:MainActivity() {
    override fun getLayoutId(): Int  = R.layout.badge_sample


    override fun initView() {
        badge.setDragCallback {

        }
    }
}