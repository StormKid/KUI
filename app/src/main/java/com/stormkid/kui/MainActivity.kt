package com.stormkid.kui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stormkid.kui_base.dimen.AutoHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val layout = frameLayout {
//            verticalLayout {
//                val name = editText()
//                button("Say Hello") {
//                    onClick { toast("Hello, ${name.text}!") }
//                }
//            }
//
//        }
        AutoHelper.BuildAuto(this).caculateViewSize()
    }
}
