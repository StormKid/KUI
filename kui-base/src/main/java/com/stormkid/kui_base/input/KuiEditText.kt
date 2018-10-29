package com.stormkid.kui_base.input

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.EditText

/**
自定义底部和placeholder 上移的edittext
@author ke_li
@date 2018/10/26
 */
class KuiEditText: EditText {

    // 底部横线左距离
    private var lineLeft = 0

    // 底部横线右距离
    private var lineRight = 0

    //动画起点
    private var startX = 0

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr){
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        if (focused){ // 开启画线动画

        }else{ // 开启画线取消动画

        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        startX = measuredWidth/2
    }

    override fun onDraw(canvas: Canvas?) {
        paint.color = Color.YELLOW
        paint.strokeWidth = 10f
        canvas?.drawLine(left.toFloat(),measuredHeight.toFloat(),measuredWidth.toFloat(),measuredHeight.toFloat(),paint)
        super.onDraw(canvas)
    }


    fun startLine(){
        val animator = ObjectAnimator.ofFloat(this,"scaleX",0f,3f,1f)


    }


}