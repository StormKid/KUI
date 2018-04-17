package com.stormkid.kui_base.dimen

import android.content.Context
import android.view.View

/**
 * 根据自定算法来计算屏幕单位量从而绘制view以达到适配效果
 * 百分比适配
 * @author like
 * @date 2018-4-17
 */
class AutoHelper(val context: Context) {
    private var perNumber=480
    /**
     * 以480等分屏幕宽度
     */
    private var width = context.resources.displayMetrics.widthPixels
    private var view: View = View(context)

    class BuildAuto(context: Context) {
        private val helper = AutoHelper(context)

        fun setView(view: View): BuildAuto {
            helper.view = view
            return this
        }

        fun  setBasePerNumber(perNumber: Int):BuildAuto{
            helper.perNumber = perNumber
            return this
        }

        /**
         * @param bottomSize 为底部虚拟栏目的高度
         */
        @Deprecated("底部虚拟栏高度适配，手写高度计算，不推荐使用，推荐最外层布局自适应屏幕")
        fun caculateViewSize(bottomSize:Int){

        }

        fun excuteSize(): AutoHelper {
            return helper
        }

    }


    fun resultSize(){
        val departPX = width/perNumber
        val attr =  view.rootView.autofillHints
    }


}