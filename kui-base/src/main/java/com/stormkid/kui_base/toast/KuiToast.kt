package com.stormkid.kui_base.toast

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Handler
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.stormkid.kui_base.R
import com.stormkid.kui_base.button.KuiButton

/**
自定义弹出土司提示
@author ke_li
@date 2018/10/23
 */
class KuiToast (private val context: Context) {

    private val kuiToastView = KuiToastView(context)
    private var rule = RelativeLayout.ALIGN_PARENT_BOTTOM
    private var animation =   R.style.alphaShow
    private var isDefault = true
    private var isAutoDismiss = true
    private val layoutparams = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
        flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    }


    companion object {
        const val TOP = 0
        const val CENTER = 1
        // 默认不带gravity方法即在底部
//        const val BOTTOM = 2

        const val NORMAL = 10
        const val CIRCLE = 11
        const val ROUND = 12

        const val TEXT_CENTER = 20
        const val TEXT_LEFT=21
        const val TEXT_RIGHT = 22


        const val LENGTH_LONG = 4000L
        const val LENGTH_SHORT = 1500L


    }

    fun showToast(text: String, time: Long) {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        layoutparams.windowAnimations = animation
        manager.addView(kuiToastView, layoutparams)
        kuiToastView.getToastViewWithoutRule().apply {  setText(text)
            if (isDefault) setBgColor(Color.DKGRAY)
        }
        if (isAutoDismiss) Handler().postDelayed({ manager.removeViewImmediate(kuiToastView) }, time)
        else kuiToastView.setOnClickListener {
            manager.removeViewImmediate(kuiToastView)
        }


    }


    inner class BuildToast {
        fun setGravity(flag: Int): BuildToast {
            when (flag) {
                KuiToast.CENTER -> rule = RelativeLayout.CENTER_IN_PARENT
                KuiToast.TOP -> rule = RelativeLayout.ALIGN_PARENT_TOP
            }
            kuiToastView.getToastView().apply {
                val params = layoutParams as RelativeLayout.LayoutParams
                params.addRule(rule,1)
            }
            return this
        }

        fun needBottom(isNeed:Boolean): BuildToast {
            animation = if (isNeed) R.style.AnimBottom
            else R.style.alphaShow
            return this
        }

        fun setDrawable(flag: Int): BuildToast {
            when (flag) {
                KuiToast.NORMAL -> {
                }
                KuiToast.CIRCLE -> kuiToastView.getToastViewWithoutRule().setBgType(KuiButton.circleType)

                KuiToast.ROUND -> kuiToastView.getToastViewWithoutRule().setBgType(KuiButton.roundType)
            }
            return this
        }

        fun setToastColor(@ColorInt color: Int): BuildToast {
            isDefault = false
            kuiToastView.getToastViewWithoutRule().setBgColor(color)
            return this
        }

        fun setBlock(isBlock: Boolean): BuildToast {
            kuiToastView.getToastViewWithoutRule().setStroke(isBlock)
            return this
        }


        fun setTextColor(@ColorRes color: Int): BuildToast {
            kuiToastView.getToastViewWithoutRule().setTextColor(color)
            return this
        }

        fun setIsAutoDissmiss(isAuto:Boolean): BuildToast {
            isAutoDismiss = isAuto
            return this
        }

        fun initToast() = this@KuiToast
    }


}