package com.stormkid.kui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = frameLayout {
            verticalLayout {
                val name = editText()
                button("Say Hello") {
                    onClick { toast("Hello, ${name.text}!") }
                }
                verticalPadding = 10
            }

        }
//        AutoHelper.BuildAuto(this).caculateViewSize()
    }
}
