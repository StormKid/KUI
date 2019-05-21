package com.stormkid.kui_base.swiper

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stormkid.kui_base.helper.BaseAdapter
import com.stormkid.kui_base.helper.RecyclerHelper

/**
swiper 的适配器
@author ke_li
@date 2019/5/21
 */
class SwiperAdapter(private val recyclerView: RecyclerView, private val init: (View) -> Unit, private val initDetail: (RecyclerView.ViewHolder, Any) -> Unit) {
    private var layoutId = 0
    /**
     * 空列表
     */
    private val list = arrayListOf<Any>()


    inner class Build {
        fun setLayout(layout: Int): Build {
            layoutId = layout
            return this
        }

        fun initList(list: ArrayList<Any>): Build {
            this@SwiperAdapter.list.clear()
            this@SwiperAdapter.list.addAll(list)
            return this
        }

        fun build() = this@SwiperAdapter
    }

    fun catchRecyclerView() {
        val layoutManager = LinearLayoutManager(recyclerView.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(SwiperDecoration())
        recyclerView.adapter = SwiperListAdapter(SwiperInit())
    }

    inner class SwiperInit : RecyclerHelper {
        override fun getContext(): Context = recyclerView.context

        override fun initItemDetail(viewHolder: RecyclerView.ViewHolder, position: Int) {
            val item = list[position]
            initDetail.invoke(viewHolder, item)
        }

        override fun initHolder(view: View) {
            init.invoke(view)
        }

        override fun initLayout(): Int = let {
            if (layoutId == 0) throw Exception("layoutId do not be zero")
            layoutId
        }

        override fun initItem(): Int = list.size

    }

    inner class SwiperListAdapter(recyclerHelper: RecyclerHelper) : BaseAdapter(recyclerHelper)

}