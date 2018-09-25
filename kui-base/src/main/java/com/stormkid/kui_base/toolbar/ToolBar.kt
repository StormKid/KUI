package com.stormkid.kui_base.toolbar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout

/**
主代码
@author ke_li
@date 2018/9/25
 */
class ToolBar: RelativeLayout{

    constructor(context: Context):this(context,null)
    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet?,defAttr:Int):super(context,attributeSet,defAttr) {
        val imageView  =   ImageView(context).apply {
            this.layoutParams = RelativeLayout.LayoutParams(40,40)
            this.setBackgroundColor(Color.RED)
        }

        this.addView(imageView)
    }



}