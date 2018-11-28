package com.stormkid.kui_base.pop

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes

/**
pop 底部弹窗
@author ke_li
@date 2018/10/19
 */
class KuiPop(private val popParams: PopParams, private val popwindowListener: PopwindowListener) {
    private var rootView: View
    private var isShow = false
    private var limitPopView: LimitPopView
    private val layoutparams = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
        flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    }

    companion object {
        const val DOWN = 0
        //        @Deprecated("左侧显示无动画")
//        const val LEFT = 1
        const val RIGHT = 2
//        @Deprecated("上部显示无动画且无对齐")
//        const val TOP = 3
    }

    init {
        rootView = LayoutInflater.from(popParams.context).inflate(popParams.layoutRes, null)
        popwindowListener.TouchView(rootView)
        limitPopView = LimitPopView(popParams.context)
    }

    /**
     * 是否需要自动对齐
     */
    fun isGravityLocation(isAutoLayout: Boolean): KuiPop {
        limitPopView.autoLayout(isAutoLayout)
        return this
    }


    /**
     * 是否需要遮罩
     */
    fun needBg(isNeed: Boolean): KuiPop {
        limitPopView.isNeedBg(isNeed)
        return this
    }

    /**
     * 默认向下弹出
     */
    fun show(view: View) {
        show(view, DOWN)
        android.R.anim.slide_in_left
    }

    /**
     * 显示popwindow
     * @param view 目标点击的view
     * @param flag 指定相对于view的位置
     */

    fun show(view: View, flag: Int) {
        val rect = Rect()
        view.getGlobalVisibleRect(rect)
        limitPopView.setFlag(flag)
        val manager = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        manager.addView(limitPopView, layoutparams)
        limitPopView.addRootView(rect) {
            limitPopView.addContentView(rootView, rect)
        }
        isShow = true
        limitPopView.setOnClickListener {
            if (isShow) limitPopView.dissmissView(rootView) {
                manager.removeViewImmediate(limitPopView)
            }
            isShow = false
        }
    }


    data class PopParams(val context: Context, @LayoutRes val layoutRes: Int)
}