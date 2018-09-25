package com.stormkid.kui_base.dimen

import android.content.Context

/**
view 适配utils
@author ke_li
@date 2018/9/25
 */
object ViewUtils {
    /**	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)	 */
    fun dip2px(context: Context, dpValue: Float) = let {
        val scale = context.resources.displayMetrics.density
        (dpValue * scale + 0.5f).toInt()
    }

    /**	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp	 */
    fun px2dip(context: Context, pxValue: Float) = let {
        val scale = context.resources.displayMetrics.density
        (pxValue / scale + 0.5f).toInt()
    }


    /**
     * sp转换成px
     */
    fun sp2px( context:Context, spValue:Float)= let{
        val fontScale=context.resources.displayMetrics.scaledDensity
         (spValue*fontScale+0.5f).toInt()
    }
    /**
     * px转换成sp
     */
    fun px2sp(context:Context, pxValue:Float)=let{
        val fontScale=context.resources.displayMetrics.scaledDensity
        (pxValue/fontScale+0.5f).toInt()
    }

}