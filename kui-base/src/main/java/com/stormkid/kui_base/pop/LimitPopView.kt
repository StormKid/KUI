package com.stormkid.kui_base.pop

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import com.stormkid.kui_base.Utils

/**
限制popview 的位置
@author ke_li
@date 2018/11/15
 */
class LimitPopView : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr)


    private val view = View(context)
    private var flag = KuiPop.DOWN
    private var isNeedBg = false
    fun addRootView(rect: Rect, callback: () -> Unit) {
        if (isNeedBg) {
            addView(view, initDirection(rect, false))
        }
        initAnimator(callback, Color.TRANSPARENT, Color.argb(120, 0, 0, 0))
    }

    fun addContentView(rootView: View, rect: Rect) {
        addView(rootView, initDirection(rect, true))
        showContent(rootView)
    }

    fun setFlag(flag: Int) {
        this.flag = flag
    }

    fun isNeedBg(isNeedBg: Boolean) {
        this.isNeedBg = isNeedBg
    }

    private fun initDirection(rect: Rect, isContent: Boolean) = FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
        when (flag) {
            KuiPop.DOWN -> {
                if (isContent)
                    leftMargin = rect.left
                topMargin = rect.bottom
            }
            KuiPop.LEFT -> {
                rightMargin = context.resources.displayMetrics.widthPixels - rect.left
                if (isContent)
                    topMargin = rect.top
            }

            KuiPop.RIGHT -> {
                leftMargin = rect.right
                if (isContent)
                    topMargin = rect.top
            }
            KuiPop.TOP -> {
                if (isContent)
                    leftMargin = rect.left
                bottomMargin = context.resources.displayMetrics.heightPixels - rect.top
            }
        }
    }


    fun dissmissView(view: View, callback: () -> Unit) {
        val params = view.layoutParams
        Utils.measureView(view)
        val height = view.measuredHeight
        val width = view.measuredWidth
        when (flag) {
            KuiPop.DOWN -> updateAnimator(height, 0, {
                initAnimator(callback, Color.argb(120, 0, 0, 0), Color.TRANSPARENT)
            }) {
                val currentHeight = it.animatedValue as Int
                params.height = currentHeight
                view.layoutParams = params
            }
            KuiPop.RIGHT -> updateAnimator(width, 0, {
                initAnimator(callback, Color.argb(120, 0, 0, 0), Color.TRANSPARENT)
            }) {
                val currentHeight = it.animatedValue as Int
                params.width = currentHeight
                view.layoutParams = params
            }
        }
    }

    private fun initAnimator(callback: () -> Unit, @ColorInt startColor: Int, @ColorInt endColor: Int) {
        ObjectAnimator.ofInt(view, "backgroundColor", startColor, endColor).apply {
            duration = 200
            setEvaluator(ArgbEvaluator())
            start()
        }.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                callback()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
    }


    private fun showContent(view: View) {
        val params = view.layoutParams
        Utils.measureView(view)
        val height = view.measuredHeight
        val width = view.measuredWidth
        when (flag) {
            KuiPop.DOWN -> updateAnimator(0, height, {}) {
                val currentHeight = it.animatedValue as Int
                params.height = currentHeight
                view.layoutParams = params
            }
            KuiPop.RIGHT -> updateAnimator(0, width, {}) {
                val currentHeight = it.animatedValue as Int
                params.width = currentHeight
                view.layoutParams = params
            }
        }
    }


    private fun updateAnimator(fromNum: Int, toNum: Int, endCallback: () -> Unit, callback: (ValueAnimator) -> Unit) {
        ValueAnimator.ofInt(fromNum, toNum).apply {
            addUpdateListener { callback(it) }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    endCallback()
                }
            })
            duration = 300
            start()
        }
    }
}