package com.stormkid.kui_base.pop

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes

/**
pop 底部弹窗
@author ke_li
@date 2018/10/19
 */
class KuiPop (private val popParams: PopParams,private  val popwindowListener: PopwindowListener) {
    private var rootView: View
    private var flag = DOWN
    private var isShow = false
    private var limitPopView:LimitPopView
    private val layoutparams = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
        flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    }

    companion object {
        const val DOWN = 0
        const val LEFT = 1
        const val RIGHT = 2
        const val TOP = 3
    }

    init {
        rootView = LayoutInflater.from(popParams.context).inflate(popParams.layoutRes,null)
        popwindowListener.TouchView(rootView)
        limitPopView = LimitPopView(popParams.context)
    }



    fun show(view: View,flag:Int){
        val rect = Rect()
        view.getGlobalVisibleRect(rect)
        val manager =  view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        manager.addView(limitPopView,layoutparams)
        limitPopView.addRootView{
            limitPopView.addRootView(rootView,rect)
        }
        isShow = true
        limitPopView.setOnClickListener {
            if (isShow)limitPopView.dissmissView { manager.removeViewImmediate(limitPopView) }
            isShow=false
        }
    }





    fun initLocation(rect: Rect){
        val left = rect.left
        val right = rect.right
        val top = rect.top
        val bottom = rect.bottom
        when(flag){
            DOWN->{}
            LEFT->{}
            RIGHT ->{}
            TOP ->{}
        }
    }

    data class PopParams( val context: Context,@LayoutRes  val layoutRes:Int)
}