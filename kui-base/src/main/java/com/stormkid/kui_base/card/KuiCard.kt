package com.stormkid.kui_base.card

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.stormkid.kui_base.Log
import com.stormkid.kui_base.R
import com.stormkid.kui_base.dimen.DimenUtils

/**
更加灵活的cardview
@author ke_li
@date 2018/9/28
 */
class KuiCard : LinearLayout {

    private var isBotton = false

    private var paddingDimen = DimenUtils.dip2px(context, 1f)

    private var radius = DimenUtils.dip2px(context, 8f).toFloat()

    private var initRadius = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)

    private var color = Color.GRAY

    private var shadeColor = R.color.primary

    private fun buildShapeDr(@ColorInt color: Int) = ShapeDrawable().apply {
        shape = RoundRectShape(initRadius, null, null)
        paint.color = color
        setPadding(paddingDimen, 0, paddingDimen, paddingDimen)

    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("Recycle")
    constructor(context: Context, attrs: AttributeSet?, def: Int) : super(context, attrs, def) {
        val a = context.obtainStyledAttributes(attrs,
                R.styleable.KuiCard, def, 0)

        radius = a.getDimensionPixelOffset(R.styleable.KuiCard_card_radius, DimenUtils.dip2px(context, 8f)).toFloat()
        color = a.getColor(R.styleable.KuiCard_card_bg, Color.GRAY)
        shadeColor = a.getResourceId(R.styleable.KuiCard_card_shade,R.color.primary)
        paddingDimen = a.getDimensionPixelOffset(R.styleable.KuiCard_evel,DimenUtils.dip2px(context, 1f))
    }

    private fun buildDownDr() = ShapeDrawable().apply {
        shape = RoundRectShape(initRadius, null, null)
        paint.color = Color.WHITE
    }

    fun setCardBg(@ColorInt color: Int) {
        this.color = color
    }

    fun setCardShadeColor(@ColorInt color: Int){
        this.shadeColor = color
    }


    fun buildDraw(view: View) {

//        val one = buildShapeDr(Color.parseColor(pingColor("0d")))
//        val two = buildShapeDr(Color.parseColor(pingColor("10")))
//        val three = buildShapeDr(Color.parseColor(pingColor("15")))
//        val four = buildShapeDr(Color.parseColor(pingColor("20")))
//        val five = buildShapeDr(Color.parseColor(pingColor("25")))

        val six = buildDownDr()
//        val layerDrawable = LayerDrawable(arrayOf(one, two, three, four, five, six))
//        view.background = layerDrawable
        Log.w(pingColor("25"))
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

    private fun getHex(int:Int)="".apply {
        var decimal = int
        var hex = ""
        while (decimal != 0) {
            val hexValue = decimal % 16
            hex = toHexChar(hexValue) + hex
            decimal /= 16
        }
    }

}