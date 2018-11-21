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
import com.stormkid.kui_base.helper.AnimationCallback

/**

@author ke_li
@date 2018/11/15
 */
class LimitPopView : FrameLayout ,AnimationCallback{
    override fun onFinished() {
    }

    override fun onStarted() {
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr)


    private  val view = View(context)
    fun addRootView(rootView:View,rect: Rect,callback: () -> Unit){
        addView(view,FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT).apply {
        })
        initAnimator(callback,Color.TRANSPARENT,Color.argb(120, 0, 0, 0))
    }

    fun initDirection(flag:Int,rect: Rect)=FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT).apply {
        when (flag) {
            KuiPop.DOWN ->
               topMargin = rect.bottom

            KuiPop.LEFT -> {
                rightMargin = context.resources.displayMetrics.widthPixels -  rect.left
                topMargin = rect.top
            }

            KuiPop.RIGHT -> {
                leftMargin = rect.right
                topMargin = rect.top
            }
            KuiPop.TOP -> {
                bottomMargin = context.resources.displayMetrics.heightPixels -  rect.top
            }
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }


    fun dissmissView(callback: () -> Unit) {
        initAnimator(callback,Color.argb(120, 0, 0, 0),Color.TRANSPARENT)
    }

    private fun initAnimator(callback: () -> Unit,@ColorInt startColor:Int,@ColorInt endColor:Int){
        ObjectAnimator.ofInt(view, "backgroundColor",startColor, endColor).apply {
            duration = 2000
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