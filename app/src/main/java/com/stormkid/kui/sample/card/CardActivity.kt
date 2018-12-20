package com.stormkid.kui.sample.card

import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import com.stormkid.kui_base.card.KuiCard
import kotlinx.android.synthetic.main.card_sample.*

/**

@author ke_li
@date 2018/12/20
 */
class CardActivity : MainActivity(){
    override fun getLayoutId(): Int = R.layout.card_sample


    override fun initView() {
        KuiCard(this).buildDraw(card)
        button.setOnClickListener {  }
    }

}