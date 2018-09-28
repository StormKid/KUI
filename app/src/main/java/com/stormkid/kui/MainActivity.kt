package com.stormkid.kui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stormkid.kui_base.dimen.DimenFit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        frameLayout {
//            verticalLayout {
//                val name = editText()
//                button("Say Hello") {
//                    onClick { toast("Hello, ${name.text}!") }
//                }
//                verticalPadding = 10
//            }
//
//        }
//        AutoHelper.BuildAuto(this).caculateViewSize()
        DimenFit.instance.setCustDensity(application,this)
        setContentView(R.layout.layout)
    }
}
