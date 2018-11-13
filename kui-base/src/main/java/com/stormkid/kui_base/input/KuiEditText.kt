package com.stormkid.kui_base.input

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
自定义底部和placeholder 上移的edittext
@author ke_li
@date 2018/10/26
 */
class KuiEditText: EditText,Animator.AnimatorListener {


    private var isAnimator = false
    private var flag = 1
    override fun onAnimationEnd(animation: Animator?) {
        clearAnimation()
        isAnimator = flag==1
    }

    override fun onAnimationStart(animation: Animator?) {
        isAnimator = false
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }

    override fun onAnimationCancel(animation: Animator?) {
    }


    //动画起点
    private var currentPoint = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 10f

    }


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr)

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused){ // 开启画线动画
            startLine()
        }else{ // 开启画线取消动画
            endLine()
        }
    }


    private val hintPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 5f
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isAnimator)
        canvas?.drawLine(left.toFloat(),measuredHeight.toFloat(),measuredWidth.toFloat(),measuredHeight.toFloat(),hintPaint)
        canvas?.drawLine(left.toFloat(),measuredHeight.toFloat(),currentPoint,measuredHeight.toFloat(),paint)
    }

    private fun setDrawLine(x:Float){
        this.currentPoint = x
        invalidate()
    }
    private var isFocus = false
    fun initEditFocus(){
        if (!isFocus) addFocusable()
        else loseFocusable()
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                initEditFocus()
            }

        }
        return super.onTouchEvent(event)
    }


    private fun startLine(){
      ObjectAnimator.ofFloat(this,"drawLine",left.toFloat(),measuredWidth.toFloat())
                .apply {
                    duration = 200
                    interpolator = AccelerateDecelerateInterpolator()
                    addListener(this@KuiEditText)
                    flag = 1
                    start()
                }

    }

    private fun endLine(){
        ObjectAnimator.ofFloat(this,"drawLine",measuredWidth.toFloat(),left.toFloat())
                .apply {
                    duration = 200
                    interpolator = AccelerateDecelerateInterpolator()
                    addListener(this@KuiEditText)
                    flag = 2
                    start()
                }
    }

    fun addSingleLine(){
        setLines(1)
        ellipsize = TextUtils.TruncateAt.END
    }

    fun addFocusable(){
       val service= context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        isFocusable = true
        isFocusableInTouchMode = true
        requestFocus()
        findFocus()
        service.showSoftInput(this,InputMethodManager.SHOW_FORCED)
        isFocus = true
    }

    fun loseFocusable(){
        val service = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        isFocusable = false
        if (service.isActive)
        service.hideSoftInputFromWindow(this.windowToken,0)
        isFocus = false
    }
}