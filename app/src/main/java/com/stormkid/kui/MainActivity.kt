package com.stormkid.kui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.stormkid.kui_base.dimen.DimenFit

abstract class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DimenFit.instance.setCustDensity(application,this)
        setContentView(getLayoutId())
        initView()
    }
    @LayoutRes
    abstract  fun  getLayoutId(): Int

    abstract fun  initView()
}
