package com.stormkid.kui_base.alert

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.stormkid.kui_base.DialogModel
import com.stormkid.kui_base.helper.AnimationCallback

/**
正常中心弹窗
@author ke_li
@date 2018/10/19
 */
class KuiAlert : DialogFragment(),AnimationCallback {
    private var dialogModel: DialogModel? = null
    companion object {
         fun instance (dialogModel:DialogModel){
             val fragment = KuiAlert()
             fragment.dialogModel = dialogModel
         }
    }


    override fun onFinished() {
    }

    override fun onStarted() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(dialogModel?.layoutId!!,container,false)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_TITLE,dialogModel?.resId!!)
        return super.onCreateDialog(savedInstanceState)
    }
}