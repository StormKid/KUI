package com.stormkid.kui_base.badge

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.annotation.ColorInt
import com.stormkid.kui_base.Utils
import com.stormkid.kui_base.dimen.DimenUtils

/**

@author ke_li
@date 2018/10/29
 */
class BadgeChangeHelper : View {
    //小型圆形中心点
    private val smallPoint = PointF()
    //大型圆形中心点
    private val bigPoint = PointF()
    //大圆半径
    private val bigCircleRadius = DimenUtils.dip2px(context, 10f)
    //小圆最大半径
    private val smallCircleRadiusMax = DimenUtils.dip2px(context, 10f)
    //小圆最小半径
    private val smallCircleRadiusMin = DimenUtils.dip2px(context, 2f)
    //新建抗锯齿画笔
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //设置颜色
    private var paintColor = Color.RED
    //实时绘制的小圆半径
    private var currentSmallRadius = 0f
    //实时测量伪装bitmap 且最后显示爆炸样式
    private var bitmap:Bitmap? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        init()
    }

    private fun init() {
        paint.color = paintColor
        //防抖动
        paint.isDither = true
    }


    fun setPaintColor(@ColorInt color: Int){
        paintColor = color
        paint.color = color
    }


    fun initPoint(px: Float, py: Float) {
        bigPoint.set(px, py)
        smallPoint.set(px, py)
        invalidate()
    }

    fun updatedPoint(px: Float, py: Float) {
        bigPoint.set(px, py)
        invalidate()
    }

    fun setBitmap(bitmap: Bitmap?){
        if (bitmap!=null)
        this.bitmap = bitmap
    }

    companion object {
        fun excute(view: View,dismissCallback: DismissCallback){
                view.setOnTouchListener(BadgeDissmissHelper(view,dismissCallback))
        }
    }


    /**
     * 绘制链接贝塞尔曲线
     */
    private fun getPath(): Path? {
        //获取两原点之间的距离
        val distance = Utils.getDistance(bigPoint, smallPoint)
        //获取当前小圆半径
        currentSmallRadius = smallCircleRadiusMax - distance.toFloat() / 10f
        if (currentSmallRadius < smallCircleRadiusMin)
            return null //如果小于小圆的范围就不用画了
        val resultPath = Path()

        //求上小圆斜率
        val x = bigPoint.x - smallPoint.x
        val y = bigPoint.y - smallPoint.y
        val tanA = Math.atan(y.toDouble() / x)

        //计算四阶拼接点

        val ax = smallPoint.x + currentSmallRadius * Math.sin(tanA).toFloat()
        val ay = smallPoint.y - currentSmallRadius * Math.cos(tanA).toFloat()


        val bx = bigPoint.x + bigCircleRadius * Math.sin(tanA).toFloat()
        val by = bigPoint.y - bigCircleRadius * Math.cos(tanA).toFloat()

        val cx = bigPoint.x - bigCircleRadius * Math.sin(tanA).toFloat()
        val cy = bigPoint.y + bigCircleRadius * Math.cos(tanA).toFloat()

        val dx = smallPoint.x - currentSmallRadius * Math.sin(tanA).toFloat()
        val dy = smallPoint.y + currentSmallRadius * Math.cos(tanA).toFloat()

        resultPath.moveTo(ax, ay)

        val controlPoint = PointF((bigPoint.x + smallPoint.x) / 2, (bigPoint.y + smallPoint.y) / 2)
        //勾勒一次贝塞尔曲线
        resultPath.quadTo(controlPoint.x, controlPoint.y, bx, by)
        resultPath.lineTo(cx, cy)
        //再次勾勒
        resultPath.quadTo(controlPoint.x, controlPoint.y, dx, dy)
        resultPath.close()
        return resultPath
    }

    inner class Builder(private val onTouchListener: com.stormkid.kui_base.badge.OnTouchListener) {


        /**
         * 为每次松手设定动画
         */
        fun insertAnimator() {
            if (currentSmallRadius > smallCircleRadiusMin) {
                // 回弹动画
                val animator = ObjectAnimator.ofFloat(1f)
                animator.duration = 250
                val start = bigPoint
                val end = smallPoint
                animator.addUpdateListener { animation ->
                    val percent = animation.animatedValue as Float// 0 - 1
                    val pointF = Utils.getPointByPercent(start, end, percent)
                    //更新point 所在的位置
                    updatedPoint(pointF.x, pointF.y)
                }
                // 设置一个差值器 在结束的时候显示回弹效果
                animator.interpolator = OvershootInterpolator(3f)
                // 还要通知自定义TouchListener,回退
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        onTouchListener.reBack()
                    }
                })
                animator.start()
            } else {
                // 显示爆炸效果
                onTouchListener.dismiss(bigPoint)
            }
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制所有显示元素
        canvas.drawCircle(bigPoint.x,bigPoint.y,bigCircleRadius.toFloat(),paint)
        val path = getPath()
        if (null!=path){
            canvas.drawCircle(smallPoint.x,smallPoint.y,currentSmallRadius,paint)
            canvas.drawPath(path,paint)
        }
        // 绘制此对象映射的图片
        if (null!=bitmap){
            canvas.drawBitmap(bitmap,bigPoint.x-bitmap?.width!!.div(2),
                    bigPoint.y-bitmap?.height!!.div(2),paint)
        }

    }
}