package com.stormkid.kui_base.tabbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stormkid.kui_base.R

/**
任意位置的tab栏目
@author ke_li
@date 2018/10/22
 */
class KuiTabbar : RecyclerView {

    /**
     * 使用tabLayout填充此布局
     */
    private val tabLayout = LinearLayout(context)

    private var tabHeight = 0

    private val tabs = arrayListOf<TabBean>()

    private var tabMode = MODE_NORMAL

    /**
     * 判断是否全部填充布局，这样计算刚刚好的tab
     */
    private var isFull = false

    companion object {
        const val MODE_NORMAL = 0
        const val MODE_TRIANGLE = 1
        const val MODE_BLOCK = 2
    }


    init {
        clipChildren = false
        clipToPadding = false
        setWillNotDraw(true)
        addView(tabLayout)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attributeSet: AttributeSet?, defAttr: Int) : super(context, attributeSet, defAttr) {
        initAttr(context, attributeSet!!, defAttr)
        layoutManager = LinearLayoutManager(context).apply {
            orientation = HORIZONTAL
        }
        adapter = TabAdapter()
    }


    private fun initAttr(context: Context, attributeSet: AttributeSet, defAttr: Int) {
        val attr = context.obtainStyledAttributes(attributeSet, R.styleable.KuiTabbar, defAttr, 0)
        val layoutHeight = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height")
        when (layoutHeight) {
            "${ViewGroup.LayoutParams.MATCH_PARENT}" -> {
            }
            "${ViewGroup.LayoutParams.WRAP_CONTENT}" -> {
            }
            else -> {
                val sysAttr = intArrayOf(android.R.attr.layout_height)
                context.obtainStyledAttributes(attributeSet, sysAttr).apply {
                    tabHeight = this.getDimensionPixelOffset(0, ViewGroup.LayoutParams.WRAP_CONTENT)
                    recycle()
                }
            }
        }
        isFull = attr.getBoolean(R.styleable.KuiTabbar_isFull, false)
        tabMode = attr.getInt(R.styleable.KuiTabbar_mode, MODE_NORMAL)
        attr.recycle()
    }

    /////////////////////////////////增删改tabs////////////////////////////////////
    /**
     *  添加tab
     */
    fun addTab(tabBean: TabBean) {
        tabs.add(tabBean)
        KuiTabbutton(context).apply {
            getButton().setText(tabBean.title)
            getBage().isDragging(true)
            getBage().setBgColor(Color.GRAY)
            getBage().setDragDismissCallback {  }
            setOnClickListener {  }
            tabLayout.addView(this)
        }
    }


    /**
     * 删除
     */
    fun deleteTab(tabBean: TabBean) = let {
        if (tabs.contains(tabBean)) {
            tabs.remove(tabBean)
            true
        } else
            false
    }

    /**
     * 修改
     */
    fun updateTab(tabBean: TabBean) = let {
        val position = tabBean.position
        try {
            val before = tabs[position]
            before.title = tabBean.title
            true
        } catch (e: Exception) {
            false
        }
    }
    ///////////////////////////////////////////////////////////////////////////


    /////////////////////输入模式////////////////////
    fun setMode(mode:Int){
        tabMode = mode
    }


    inner class TabAdapter:Adapter<TabHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabHolder = TabHolder(KuiTabbutton(context))

        override fun getItemCount(): Int = tabs.size

        override fun onBindViewHolder(holder: TabHolder, position: Int) {
        }

    }


    inner class TabHolder(view:View):ViewHolder(view){

    }
}