package com.stormkid.kui.sample.alert

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.stormkid.kui.MainActivity
import com.stormkid.kui.R
import com.stormkid.kui_base.ColorResButton
import com.stormkid.kui_base.DialogModel
import com.stormkid.kui_base.alert.AlertContentListener
import com.stormkid.kui_base.alert.KuiAlert
import com.stormkid.kui_base.alert.KuiChoiceAlert
import kotlinx.android.synthetic.main.alert_sample.*

/**

@author ke_li
@date 2018/10/23
 */
class AlertActivity : MainActivity(),AlertContentListener {
    override fun beforeDismiss() {
    }

    private var count = 0

    override fun contentViewEvents(view: View,dialog: DialogFragment) {
       view.findViewById<View>(R.id.yes).setOnClickListener { dialog.dismiss() }
    }

    override fun getLayoutId(): Int = R.layout.alert_sample

    override fun initView() {
        // 可以通过自己构建自定义类型的alert来完成对alert整个的处理
        show_normal_alert.setOnClickListener {
            val alert = KuiAlert.instance(DialogModel(R.layout.alert_self_content, 0),this)
            alert.show(supportFragmentManager, "1")
        }

        //TODO 等toast做好用自己写的toast
        show_choose_alert.setOnClickListener {
            count +=1
            if (count%2 ==0){
                KuiChoiceAlert.instance.setTitle("这是偶数点击提示")
                        .setPositiveText("偶数确定比较长")
                        .setContent("偶数提示内容比较少")
                        .initClickDismiss(true)
                        .initCallback({
                            Toast.makeText(this@AlertActivity,"偶数确定提示",Toast.LENGTH_SHORT).show()
                        },{
                            Toast.makeText(this@AlertActivity,"偶数取消提示",Toast.LENGTH_SHORT).show()
                        }).build().show(supportFragmentManager,"2")
            }else {
                KuiChoiceAlert.instance.setTitle("这是奇数点击提示")
                        .initClickDismiss(true)
                        .setNegativeText("奇数的取消有点长")
                        .setContent("奇数提示内容比较多，以下为复制：啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊")
                        .initCallback({
                            Toast.makeText(this@AlertActivity,"奇数确定提示",Toast.LENGTH_SHORT).show()
                },{
                            Toast.makeText(this@AlertActivity,"奇数取消提示",Toast.LENGTH_SHORT).show()
                }).build().show(supportFragmentManager,"3")
            }
        }

        show_colorful_choose_alert.setOnClickListener {
            KuiChoiceAlert.instance.setTitleColor(Color.BLUE).setNegativeButtonColorful(ColorResButton(textColor = android.R.color.white)).setPositiveButtonColorful(
                    ColorResButton(bgColor = Color.YELLOW,rippleColor = Color.YELLOW,textColor = android.R.color.holo_orange_light,isStroke = true)).setTitle("显示多种颜色").setContentColor(Color.RED).setContent("嘿嘿嘿").initCallback({},{}).build().show(supportFragmentManager,"4")
        }


    }
}