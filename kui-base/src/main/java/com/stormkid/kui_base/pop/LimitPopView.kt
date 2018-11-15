package com.stormkid.kui_base.pop

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt

/**

@author ke_li
@date 2018/11/15
 */
class LimitPopView : FrameLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr)

    fun addRootView(callback: () -> Unit): LimitPopView {
        initAnimator(callback,Color.TRANSPARENT,Color.argb(120, 0, 0, 0))
        return this
    }


    fun addRootView(rootView:View,rect: Rect){
        addView(rootView,FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT).apply {
                val bottom = rect.bottom
                topMargin = bottom
//                leftMargin = rect.right
//                topMargin = rect.top
        })
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }


    fun dissmissView(callback: () -> Unit) {
        initAnimator(callback,Color.argb(120, 0, 0, 0),Color.TRANSPARENT)
    }

    private fun initAnimator(callback: () -> Unit,@ColorInt startColor:Int,@ColorInt endColor:Int){
        ObjectAnimator.ofInt(this, "backgroundColor",startColor, endColor).apply {
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


}