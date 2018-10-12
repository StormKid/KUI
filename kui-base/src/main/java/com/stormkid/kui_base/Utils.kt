package com.stormkid.kui_base

import android.graphics.Paint
import android.support.graphics.drawable.VectorDrawableCompat
import android.view.View

/**
各种工具
@author ke_li
@date 2018/9/29
 */
object Utils {
    val paint by lazy {Paint()}

    /**
     * 给imageView 的svg初始化颜色
     */
    fun initSvgColor(initImgRes: InitImgRes){
        val res  = initImgRes.context.resources
        val theme = initImgRes.context.theme
        val vectorDrawableCompat = VectorDrawableCompat.create(res,initImgRes.imgRes,theme) ?: return
        vectorDrawableCompat.setTint(res.getColor(initImgRes.colorRes))
        initImgRes.imageView.setImageDrawable(vectorDrawableCompat)
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