package com.stormkid.kui.sample.toast

import androidx.core.content.ContextCompat
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
            KuiToast(this).showToast("弹出普通toast",KuiToast.LENGTH_SHORT)
        }

        show_self_toast.setOnClickListener {
            KuiToast(this).BuildToast()
                    .setBlock(true).setDrawable(KuiToast.ROUND)
                    .setToastColor(ContextCompat.getColor(this,R.color.primary))
                    .setTextColor(R.color.primary).initToast()
                    .showToast("弹出自定义toast",KuiToast.LENGTH_SHORT)
        }

        show_middle_toast.setOnClickListener {
            KuiToast(this).BuildToast().setGravity(KuiToast.CENTER).setDrawable(KuiToast.CIRCLE).
                    initToast().showToast("弹出中部toast",KuiToast.LENGTH_SHORT)
        }

        show_top_toast.setOnClickListener {
            KuiToast(this).BuildToast().setGravity(KuiToast.TOP).
                    initToast().showToast("弹出顶部toast",KuiToast.LENGTH_SHORT)
        }

        show_bottom_toast.setOnClickListener {
            KuiToast(this).BuildToast().needBottom(true).
                    initToast().showToast("底部弹出动画的toast",KuiToast.LENGTH_SHORT)
        }
    }
}