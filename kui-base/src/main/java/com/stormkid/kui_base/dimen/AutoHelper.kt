package com.stormkid.kui_base.dimen

import android.content.Context
import android.view.View

class AutoHelper(val context: Context) {

    class BuildAuto(context: Context) {
        private val height = context.resources.displayMetrics.heightPixels
        private val width = context.resources.displayMetrics.widthPixels

        private var view: View = View(context)

        fun setView(view: View) {
            this.view = view
        }

        fun  checkParent(){
        }

    }

}