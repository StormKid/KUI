package com.stormkid.kui.sample.card

import android.graphics.Color
import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import kotlinx.android.synthetic.main.card_sample.*

/**

@author ke_li
@date 2018/12/20
 */
class CardActivity : MainActivity(){
    override fun getLayoutId(): Int = R.layout.card_sample


    override fun initView() {
        left_card.buttonView().setText("←按钮")
        left_card.setOnClickListener {  }
        left_card.buttonView().setRippleColor(Color.CYAN)
        mid_card.buttonView().setText("中间按钮")
        mid_card.buttonView().setRippleColor(Color.RED)
        mid_card.setOnClickListener {  }
        right_card.buttonView().setText("→按钮")
        right_card.buttonView().setRippleColor(Color.YELLOW)
        right_card.setOnClickListener {  }
    }

}