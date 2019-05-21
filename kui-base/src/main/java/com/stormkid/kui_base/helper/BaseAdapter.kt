package com.stormkid.kui_base.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**

@author ke_li
@date 2019/5/21
 */
abstract class BaseAdapter (private val recyclerHelper: RecyclerHelper): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHodler = let{
        val layout =   LayoutInflater.from(recyclerHelper.getContext()).inflate(recyclerHelper.initLayout(),parent,false)
        BaseViewHodler(layout)
    }

    override fun getItemCount(): Int= recyclerHelper.initItem()
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        recyclerHelper.initItemDetail(holder,position)
    }

    inner class BaseViewHodler(view: View):RecyclerView.ViewHolder(view){
        init {
            recyclerHelper.initHolder(view)
        }
    }
}