package com.stormkid.kui_base.helper

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

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        animation?.cancel()
        animationCallback.onFinished()
    }

    override fun onAnimationStart(animation: Animation?) {
        animationCallback.onStarted()
    }

    /**
     * 淡入淡出
     */
    fun alphaAnimation(animateModel: AlphaAnimateModel) {
        val animate = AlphaAnimation(animateModel.from, animateModel.to)
        startAnimate(animateModel,animate)
    }

    /**
     * 自身旋转
     */
    fun rotateAnimation( animationModel: RotateAnimationModel){
        val animate = RotateAnimation(animationModel.fromDegree,animationModel.toDegree,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        startAnimate(animationModel,animate)
    }

    /**
     * 位移动画
     */
    fun translateAnimation(animationModel: TransLateOrScaleAnimationModel){
        val animate = TranslateAnimation(animationModel.fromX,animationModel.toX,animationModel.fromY,animationModel.toY)
        startAnimate(animationModel,animate)
    }

    /**
     * 自身放大或缩小
     */
    fun scaleAnimation(animationModel: TransLateOrScaleAnimationModel){
        val animate = ScaleAnimation(animationModel.fromX,animationModel.toX,animationModel.fromY,animationModel.toY,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        startAnimate(animationModel,animate)
    }

    private fun startAnimate(animateValue: AnimateValue,animate: Animation){
        animateValue.view.animation = animate
        animate.duration = animateValue.duration
        animate.setAnimationListener(this)
        animate.start()
    }

}