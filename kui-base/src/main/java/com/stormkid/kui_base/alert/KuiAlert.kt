package com.stormkid.kui_base.alert

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.stormkid.kui_base.DialogModel

/**
正常中心弹窗
@author ke_li
@date 2018/10/19
 */
class KuiAlert : DialogFragment() {
    private var dialogModel: DialogModel? = null
    private var alertContentListener:AlertContentListener? = null
    companion object {
         fun instance (dialogModel:DialogModel,alertContentListener: AlertContentListener): KuiAlert {
             val fragment = KuiAlert()
             fragment.dialogModel = dialogModel
             fragment.alertContentListener=alertContentListener
             return fragment
         }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(dialogModel?.layoutId!!,container,false).apply {
            if (alertContentListener!=null) alertContentListener?.contentViewEvents(this,this@KuiAlert)
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_TITLE,0)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        if (null!=alertContentListener)alertContentListener?.beforeDismiss()
        super.onDismiss(dialog)
    }

}