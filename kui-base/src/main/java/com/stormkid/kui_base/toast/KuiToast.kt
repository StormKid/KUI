package com.stormkid.kui_base.toast

import android.content.Context
import android.graphics.PixelFormat
import android.os.Handler
import android.view.Gravity
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
class KuiToast(private val context: Context) {

    private val kuiToastView = KuiToastView(context)
    private var rule = RelativeLayout.ALIGN_PARENT_BOTTOM
    private var isAutoDismiss = true
    private val layoutparams = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
        flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        windowAnimations = R.style.AnimBottom
        gravity = Gravity.BOTTOM
    }

    companion object {
        const val TOP = 0
        const val CENTER = 1
        const val BOTTOM = 2

        const val NORMAL = 10
        const val CIRCLE = 11
        const val ROUND = 12

        const val LENGTH_LONG = 4000L
        const val LENGTH_SHORT = 1000L

    }

    fun showToast(text: String, time: Long) {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        manager.addView(kuiToastView, layoutparams)
        kuiToastView.getToastViewWithoutRule().setText(text)
        if (isAutoDismiss) Handler().postDelayed({ manager.removeViewImmediate(kuiToastView) }, time)
        else kuiToastView.setOnClickListener {
            manager.removeViewImmediate(kuiToastView)
        }

    }


    inner class BuildToast {

        fun setGravity(flag: Int): BuildToast {
            when (flag) {
                KuiToast.BOTTOM -> rule = RelativeLayout.ALIGN_PARENT_BOTTOM
                KuiToast.CENTER -> rule = RelativeLayout.CENTER_IN_PARENT
                KuiToast.TOP -> rule = RelativeLayout.ALIGN_PARENT_TOP
            }
            kuiToastView.getToastView().apply {
                val params = layoutParams as RelativeLayout.LayoutParams
                params.addRule(rule)
            }
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
            kuiToastView.getToastViewWithoutRule().setBgColor(color)
            return this
        }

        fun setBlock(isBlock: Boolean): BuildToast {
            kuiToastView.getToastViewWithoutRule().setStroke(isBlock)
            return this
        }

        fun setTextGravity(gravity: Int): BuildToast {
            kuiToastView.getToastViewWithoutRule().setTextGravity(gravity)
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