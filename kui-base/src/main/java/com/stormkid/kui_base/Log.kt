package com.stormkid.kui_base

import android.util.Log

/**
 打印log的util
@author ke_li
@date 2018/10/26
 */
object Log {
    private const val isShowLog = true
    private const val KUI_TYPE = "KUI-LOG"

    fun i (flag:String,massage:Any?){
        val s = massage.toString()
        if (isShowLog)
        Log.i(flag,s)
    }

    fun i (massage: Any?){
        i(KUI_TYPE,massage)
    }

    fun d (flag:String,massage:Any?){
        val s = massage.toString()
        if (isShowLog)
        Log.d(flag,s)
    }

    fun d (massage: Any?){
        d(KUI_TYPE,massage)
    }

    fun e (flag:String,massage:Any?){
        val s = massage.toString()
        if (isShowLog)
        Log.e(flag,s)
    }

    fun e (massage: Any?){
        e(KUI_TYPE,massage)
    }

    fun w (flag:String,massage:Any?){
        val s = massage.toString()
        if (isShowLog)
        Log.w(flag,s)
    }

    fun w (massage: Any?){
        w(KUI_TYPE,massage)
    }
}