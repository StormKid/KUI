package com.stormkid.kui_base.badge

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.stormkid.kui_base.Utils

/**
 MVP 模式处理回调
@author ke_li
@date 2018/10/29
 */
class BadgeDraggingHelper(private val view: View, private val dissmissCallback: DismissCallback): View.OnTouchListener {
    // 获取windowManager 遮罩长按移动
    private val windowManager: WindowManager = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    // 设置透明manager
    private val params = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
        flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    }
    private val badgeChangeHelper = BadgeDragView(view.context)
    private var myBuild:BadgeDragView.Builder? = null

    init {
        val color = view.tag ?:0
        if (color!=0) badgeChangeHelper.setPaintColor(color as Int)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        when(event?.action){
            //按下时通过windowManager启动滑动操作
            MotionEvent.ACTION_DOWN ->{
                v?.visibility = View.INVISIBLE
                windowManager.addView(badgeChangeHelper,params)
                val location = intArrayOf(0,0)
                v?.getLocationOnScreen(location)
                // 构建bitmap虚拟图层
                val bitMap = Utils.getBitMap(v!!)
                // 获取view中心点
                badgeChangeHelper.initPoint(location[0].plus(v.width/2f),location[1].plus(v.height/2f))
                badgeChangeHelper.setBitmap(bitMap)
                myBuild =  badgeChangeHelper.Builder(object :OnTouchListener{
                    override fun reBack() {
                        windowManager.removeView(badgeChangeHelper)
                        v.visibility = View.VISIBLE
                    }

                    override fun dismiss(pointF: PointF) { // 取消后处理动画
                        windowManager.removeView(badgeChangeHelper)
                        dissmissCallback.dismissed(v)
                        v.visibility - View.GONE
                    }

                })


            }
            // 滑动开始计算距离
            MotionEvent.ACTION_MOVE->
                badgeChangeHelper.updatedPoint(event.rawX,event.rawY)

            MotionEvent.ACTION_UP-> myBuild?.insertAnimator()

        }
        return true
    }




    companion object {
        fun draging(view: View,dismissCallback: DismissCallback){
            view.setOnTouchListener(BadgeDraggingHelper(view,dismissCallback))
        }
    }
}