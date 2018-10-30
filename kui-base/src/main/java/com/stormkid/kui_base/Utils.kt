package com.stormkid.kui_base

import android.content.Context
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.stormkid.kui_base.dimen.DimenUtils

/**
各种工具
@author ke_li
@date 2018/9/29
 */
object Utils {


    private fun tintDrawable(drawable: Drawable, colors: Int): Drawable {
        val wrap = DrawableCompat.wrap(drawable).mutate()
        DrawableCompat.setTint(wrap, colors)
        return wrap
    }

    /**
     * 给imageView 的svg初始化颜色
     */
    fun initSvgColor(initImgRes: InitImgRes) {
        //利用ContextCompat工具类获取drawable图片资源
        val drawable = ContextCompat.getDrawable(initImgRes.context, initImgRes.imgRes) ?: return
        //简单的使用tint改变drawable颜色
        val drawableResult = tintDrawable(drawable, ContextCompat.getColor(initImgRes.context, initImgRes.colorRes))
        initImgRes.imageView.setImageDrawable(drawableResult)
    }

    /**
     * 测量view 的宽高
     */
    fun measureView(view: View): View {
        val w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED)
        val h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED)
        view.measure(w, h)
        return view
    }

    /**
     * 获得两点之间的距离
     */
    fun getDistance(point1: PointF, point2: PointF): Double {
        return Math.sqrt(((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y)).toDouble())
    }


    /**
     * 根据分度值，计算从start到end中，fraction位置的值。fraction范围为0 -> 1
     *
     * @param fraction
     * @param start
     * @param end
     */
    fun evaluateValue(fraction: Float, start: Number, end: Number): Float {
        return start.toFloat() + (end.toFloat() - start.toFloat()) * fraction
    }

    /**
     * Get point between p1 and p2 by percent. 根据百分比获取两点之间的某个点坐标
     *
     * @param p1
     * @param p2
     * @param percent
     */
    fun getPointByPercent(p1: PointF, p2: PointF, percent: Float): PointF {
        return PointF(evaluateValue(percent, p1.x, p2.x), evaluateValue(
                percent, p1.y, p2.y))
    }


    /**
     * 获取状态栏高度
     *
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        //获取status_bar_height资源的ID
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            context.resources.getDimensionPixelSize(resourceId)
        } else DimenUtils.dip2px(context,25f)
    }
}