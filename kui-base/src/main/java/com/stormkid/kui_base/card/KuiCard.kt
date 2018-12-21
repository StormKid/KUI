package com.stormkid.kui_base.card

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.stormkid.kui_base.R
import com.stormkid.kui_base.button.KuiButton
import com.stormkid.kui_base.dimen.DimenUtils

/**
更加灵活的cardview
@author ke_li
@date 2018/9/28
 */
class KuiCard : LinearLayout {

    /**
     * 是否是按钮
     */
    private var isButton = false

    /**
     * 阴影尺寸
     */
    private var paddingDimen = DimenUtils.dip2px(context, 1f)

    /**
     * card圆弧
     */
    private var radius = DimenUtils.dip2px(context, 8f).toFloat()

    /**
     * card计算最终圆弧
     */
    private var initRadius = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)

    /**
     * card背景色
     */
    private var color = Color.WHITE

    /**
     * card 阴影色
     */
    private var shadeColor = R.color.primary

    private val button = KuiButton(context)

    /**
     * card 阴影的模式
     */
    private var shadeMode = SHAPE_RIGHT_BOTTOM

    companion object {
        const val SHAPE_LEFT_TOP = 0
        const val SHAPE_LEFT_BOTTOM = 1
        const val SHAPE_RIGHT_TOP = 2
        const val SHAPE_RIGHT_BOTTOM = 3
        const val ALL = 4
    }

    private fun buildShapeDr(@ColorInt color: Int) = ShapeDrawable().apply {
        shape = RoundRectShape(initRadius, null, null)
        paint.color = color
        when(shadeMode){
            SHAPE_LEFT_TOP->setPadding(paddingDimen, paddingDimen, 0, 0)
            SHAPE_LEFT_BOTTOM->setPadding(paddingDimen, 0, 0, paddingDimen)
            SHAPE_RIGHT_TOP ->setPadding(0, paddingDimen, paddingDimen, 0)
            SHAPE_RIGHT_BOTTOM->setPadding(0, 0, paddingDimen, paddingDimen)
            ALL->setPadding(paddingDimen, paddingDimen, paddingDimen, paddingDimen)
        }

    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("Recycle")
    constructor(context: Context, attrs: AttributeSet?, def: Int) : super(context, attrs, def) {
        val a = context.obtainStyledAttributes(attrs,
                R.styleable.KuiCard, def, 0)

        radius = a.getDimensionPixelOffset(R.styleable.KuiCard_card_radius, DimenUtils.dip2px(context, 8f)).toFloat()
        color = a.getColor(R.styleable.KuiCard_card_bg, Color.WHITE)
        shadeColor = a.getResourceId(R.styleable.KuiCard_card_shade,android.R.color.darker_gray)
        paddingDimen = a.getDimensionPixelOffset(R.styleable.KuiCard_evel,DimenUtils.dip2px(context, 1f))
        initRadius = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        isButton = a.getBoolean(R.styleable.KuiCard_is_card_button,false)
        shadeMode = a.getInt(R.styleable.KuiCard_shape_mode,SHAPE_RIGHT_BOTTOM)
        bottonShow()
        buildDraw()
    }

    private fun buildDownDr() = ShapeDrawable().apply {
        shape = RoundRectShape(initRadius, null, null)
        paint.color = color
    }

    fun setCardBg(@ColorInt color: Int) {
        this.color = color
    }

    fun setCardShadeColor(@ColorInt color: Int){
        this.shadeColor = color
    }


    private fun buildDraw() {
        val one = buildShapeDr(Color.parseColor(pingColor("0d")))
        val two = buildShapeDr(Color.parseColor(pingColor("10")))
        val three = buildShapeDr(Color.parseColor(pingColor("15")))
        val four = buildShapeDr(Color.parseColor(pingColor("20")))
        val five = buildShapeDr(Color.parseColor(pingColor("25")))
        val six = buildDownDr()
        val layerDrawable = LayerDrawable(arrayOf(one, two, three, four, five, six))
        this.background = layerDrawable
    }


    private fun pingColor(alpha: String)=StringBuffer().let {
        val color = ContextCompat.getColor(context,shadeColor)
        it.append("#$alpha")
        it.append(getHex(Color.red(color)))
        it.append(getHex(Color.green(color)))
        it.append(getHex(Color.blue(color)))
        it.toString()
    }

   private fun toHexChar(hexValue: Int): Char {
        return if (hexValue in 0..9)
            (hexValue + '0'.toInt()).toChar()
        else
            (hexValue - 10 + 'A'.toInt()).toChar()
    }

    private fun getHex(int:Int)=let{
        var decimal = int
        var hex = ""
        while (decimal != 0) {
            val hexValue = decimal % 16
            hex = toHexChar(hexValue) + hex
            decimal /= 16
        }
        hex
    }

    private fun bottonShow(){
        if (isButton){
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
            if (color==Color.WHITE)color=ContextCompat.getColor(context,R.color.primary)
            button.setBgColor(color)
            button.layoutParams = lp
            shadeColor = R.color.primary
            button.setRadius(radius)
            addView(button)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }


    fun buttonView() = button

}