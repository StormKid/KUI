package com.stormkid.kui_base.badge

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import com.stormkid.kui_base.Utils
import com.stormkid.kui_base.dimen.DimenUtils

/**
 MVP 模式处理回调
@author ke_li
@date 2018/10/29
 */
class BadgeDissmissHelper(private val context: Context,private val dissmissCallback: DismissCallback): View.OnTouchListener {
    // 获取windowManager 遮罩长按移动
    private val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    // 设置透明manager
    private val params = WindowManager.LayoutParams().apply {
        format = PixelFormat.TRANSPARENT
    }
    private val badgeChangeHelper = BadgeChangeHelper(context)
    private val frameLayout = FrameLayout(context)
    private val imageView = ImageView(context).apply {
         layoutParams = FrameLayout.LayoutParams(DimenUtils.dip2px(context,30f),DimenUtils.dip2px(context,30f))
         frameLayout.addView(this)
    }
    private var myBuild:BadgeChangeHelper.Builder? = null
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {


        when(event?.action){
            //按下时通过windowManager启动滑动操作
            MotionEvent.ACTION_DOWN ->{
                v?.visibility = View.INVISIBLE
                windowManager.addView(badgeChangeHelper,params)
                val location = intArrayOf(0,0)
                v?.getLocationOnScreen(location)
                // 构建bitmap虚拟图层
                val bitMap = getBitMap(v!!)
                // 获取view中心点
                badgeChangeHelper.initPoint(location[0].plus(v.width/2f),location[1].plus(v.height/2f)-Utils.getStatusBarHeight(context))
                badgeChangeHelper.setBitmap(bitMap)
                myBuild =  badgeChangeHelper.Builder(object :OnTouchListener{
                    override fun reBack() {
                        windowManager.removeView(badgeChangeHelper)
                        v.visibility = View.VISIBLE
                    }

                    override fun dismiss(pointF: PointF) { // 取消后处理动画
                        windowManager.removeView(badgeChangeHelper)
                        dissmissCallback.dismissed(v)
                    }

                })


            }
            // 滑动开始计算距离
            MotionEvent.ACTION_MOVE->
                badgeChangeHelper.updatedPoint(event.rawX,event.rawY-Utils.getStatusBarHeight(context))

            MotionEvent.ACTION_UP-> myBuild?.insertAnimator()

        }
        return true
    }

    private fun getBitMap(view: View): Bitmap? {
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bitmap = view.drawingCache
        view.isDrawingCacheEnabled = false
        view.destroyDrawingCache()
        return bitmap
    }
}