package com.stormkid.kui_base.swiper

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
轮播图，滑动图片显示
@author ke_li
@date 2018/10/19
 */
class KuiSwiper: RecyclerView{

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr){
             SwiperAdapter(this,{

             },{
                 viewHolder, any ->

             })
    }




}