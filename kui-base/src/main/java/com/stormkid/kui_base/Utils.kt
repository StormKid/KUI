package com.stormkid.kui_base

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
各种工具
@author ke_li
@date 2018/9/29
 */
object Utils {

    private fun  tintDrawable( drawable:Drawable, colors:Int): Drawable {
        val wrap = DrawableCompat.wrap(drawable).mutate()
        DrawableCompat.setTint(wrap,colors)
        return wrap
    }

    /**
     * 给imageView 的svg初始化颜色
     */
    fun initSvgColor(initImgRes: InitImgRes){
        //利用ContextCompat工具类获取drawable图片资源
        val drawable = ContextCompat.getDrawable(initImgRes.context, initImgRes.imgRes)?:return
        //简单的使用tint改变drawable颜色
        val drawableResult = tintDrawable(drawable,ContextCompat.getColor(initImgRes.context, initImgRes.colorRes))
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
}