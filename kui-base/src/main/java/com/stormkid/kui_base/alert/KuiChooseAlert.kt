package com.stormkid.kui_base.alert

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.DialogFragment
import com.stormkid.kui_base.ColorResButton
import com.stormkid.kui_base.DialogModel
import com.stormkid.kui_base.InitDrawable
import com.stormkid.kui_base.R
import com.stormkid.kui_base.button.KuiButton
import com.stormkid.kui_base.drawables.BgDrawable

/**

@author ke_li
@date 2018/10/23
 */
class KuiChooseAlert private constructor() : AlertContentListener {
    override fun beforeDismiss() {
        initData()
    }

    private lateinit var yes: () -> Unit
    private lateinit var cancel: () -> Unit
    private lateinit var title :String
    private lateinit var content :String
    private lateinit var positiveText:String
    private lateinit var negativeText:String
    private  var isClickAllDismiss = false
    private  var titleColor = 0
    private  var contentColor = 0
    private lateinit var positiveColorButton : ColorResButton
    private lateinit  var negativeColorButton:ColorResButton
    init {
        initData()
    }
    override fun contentViewEvents(view: View, dialog: DialogFragment) {
        BgDrawable.instance.getRadiusDrawable(InitDrawable(Color.WHITE, view, 5f, false))
        view.findViewById<TextView>(R.id.title).apply {
            text = title
            setTextColor(titleColor)
        }
        view.findViewById<TextView>(R.id.content).apply {
            text = content
            maxLines = 4
            ellipsize = TextUtils.TruncateAt.END
            setTextColor(contentColor)
        }
        view.findViewById<KuiButton>(R.id.yes).apply {
            setText(positiveText)
            setTextColor(positiveColorButton.textColor)
            setBgColor(positiveColorButton.bgColor)
            setStroke(positiveColorButton.isStroke)
            setRippleColor(positiveColorButton.rippleColor)
            setOnClickListener {
                dialog.dismiss()
                yes()
            }
        }
        view.findViewById<KuiButton>(R.id.cancel).apply {
            setText(negativeText)
            setStroke(negativeColorButton.isStroke)
            setTextColor(negativeColorButton.textColor)
            setBgColor(negativeColorButton.bgColor)
            setRippleColor(negativeColorButton.rippleColor)
            setOnClickListener {
                dialog.dismiss()
                cancel()
            }
        }

        if (isClickAllDismiss) view.setOnClickListener { dialog.dismiss() }
    }

    companion object {
        val instance by lazy { KuiChooseAlert() }
    }

    /**
     * 更新是否点击全部即可消失窗口
     */
    fun initClickDismiss(isClickAllDismiss: Boolean): KuiChooseAlert {
        this.isClickAllDismiss = isClickAllDismiss
        return this
    }

    /**
     * 赋值callback
     */

   fun initCallback(okCallback: () -> Unit, cancelCallback: () -> Unit): Builder {
        this.yes = okCallback
        this.cancel = cancelCallback
        return Builder()
    }

    /**
     * 设置弹窗标题
     */
    fun setTitle(title: String): KuiChooseAlert {
        this.title = title
        return this
    }

    /**
     * 设置主要提示的内容
     */
    fun setContent(content: String): KuiChooseAlert {
        this.content = content
        return this
    }

    /**
     * 设置确定点击按钮文字
     */
    fun setPositiveText(content: String): KuiChooseAlert {
        if (content.length > 6) this.positiveText = content.substring(0, 5)
        this.positiveText = this.positiveText + "..."
        return this
    }

    /**
     * 设置取消点击按钮的文字
     */
    fun setNegativeText(content: String): KuiChooseAlert {
        if (content.length > 6) this.negativeText = content.substring(0, 5)
        this.negativeText = this.negativeText + "..."
        return this
    }

    /**
     * 设置ok按钮颜色属性
     */
    fun setPositiveButtonColorful(colorResButton: ColorResButton): KuiChooseAlert {
        this.positiveColorButton = colorResButton
        return this
    }

    /**
     * 设置cancel按钮颜色属性
     */
    fun setNegativeButtonColorful(colorResButton: ColorResButton): KuiChooseAlert {
        this.negativeColorButton = colorResButton
        return this
    }

    inner class Builder {
        /**
         * 构建窗体
         */
        fun build(): KuiAlert {
            return KuiAlert.instance(DialogModel(R.layout.alert_conetent, 0), this@KuiChooseAlert)
        }

    }
    /**
     * 设置标题颜色
     */
    fun setTitleColor(@ColorInt colorInt: Int): KuiChooseAlert {
        this.titleColor = colorInt
        return this
    }

    /**
     * 设置中间显示的颜色
     */
    fun setContentColor(@ColorInt colorInt: Int): KuiChooseAlert {
        this.contentColor = colorInt
        return this
    }

    private fun initData() {
        isClickAllDismiss = false
        title = "提示"
        content = "是否干啥"
        positiveText = "确定"
        negativeText = "取消"
        positiveColorButton= ColorResButton()
        negativeColorButton= positiveColorButton
        titleColor = Color.rgb(51, 51, 51)
        contentColor = Color.rgb(102, 102, 102)

    }
}