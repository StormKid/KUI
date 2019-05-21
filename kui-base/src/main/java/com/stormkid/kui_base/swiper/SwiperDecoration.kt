package com.stormkid.kui_base.swiper

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**

@author ke_li
@date 2019/5/21
 */
class SwiperDecoration : RecyclerView.ItemDecoration() {

    /**
     * 是否显示浮动样式
     */
    private var isShowPointRange = false


    /**
     * 绘制浮动样式
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    }

    /**
     * 绘制底层样式
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    }
}