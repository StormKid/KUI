package com.stormkid.kui_base.helper

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
init RecyclerView 的约束
@author ke_li
@date 2019/5/21
 */
interface RecyclerHelper {
    fun getContext():Context
    fun initItemDetail(viewHolder: RecyclerView.ViewHolder,position:Int)
    fun initHolder (view: View)
    fun initLayout():Int
    fun initItem():Int
}