package com.stormkid.kui_base.helper

import android.view.View
import android.view.animation.*
import com.stormkid.kui_base.AlphaAnimateModel
import com.stormkid.kui_base.RotateAnimationModel
import com.stormkid.kui_base.TransLateOrScaleAnimationModel

/**
自定义单步骤动画方案
@author ke_li
@date 2018/10/22
 */
class AnimationHelpers constructor(private val animationCallback: AnimationCallback) : Animation.AnimationListener {

    private var needSet:Int

    init {
        needSet = 0
    }


    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        animation?.cancel()
        animationCallback.onFinished()
    }

    override fun onAnimationStart(animation: Animation?) {
        animationCallback.onStarted()
    }
    fun initSet(): AnimationHelpers {
        needSet = 1
        return this
    }

    /**
     * 淡入淡出
     */
    fun alphaAnimation(animateModel: AlphaAnimateModel): AlphaAnimation {
        val animate = AlphaAnimation(animateModel.from, animateModel.to)
        startAnimate(animateModel, animate)
        return animate

    }

    /**
     * 自身旋转
     */
    fun rotateAnimation(animationModel: RotateAnimationModel): RotateAnimation {
        val animate = RotateAnimation(animationModel.fromDegree, animationModel.toDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        startAnimate(animationModel, animate)
        return animate
    }

    /**
     * 位移动画
     */
    fun translateAnimation(animationModel: TransLateOrScaleAnimationModel): TranslateAnimation {
        val animate = TranslateAnimation(Animation.RELATIVE_TO_PARENT,animationModel.fromX, Animation.RELATIVE_TO_PARENT, animationModel.toX ,Animation.RELATIVE_TO_PARENT, animationModel.fromY, Animation.RELATIVE_TO_PARENT, animationModel.toY)
        startAnimate(animationModel, animate)
        return animate
    }

    /**
     * 自身放大或缩小
     */
    fun scaleAnimation(animationModel: TransLateOrScaleAnimationModel): ScaleAnimation {
        val animate = ScaleAnimation(animationModel.fromX, animationModel.toX, animationModel.fromY, animationModel.toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        startAnimate(animationModel, animate)
        return animate
    }

    private fun startAnimate(animateValue: AnimateValue, animate: Animation) {
        animate.duration = animateValue.duration
        animate.setAnimationListener(this)
        if (needSet == 0)
            animateValue.view.startAnimation(animate)
    }


    inner class BuildSet {

        fun build(view: View,vararg animation: Animation) =
            AnimationSet(false).apply {
                setAnimationListener(this@AnimationHelpers)
                animation.forEach {
                    addAnimation(it)
                }
                view.startAnimation(this)
            }

    }

}