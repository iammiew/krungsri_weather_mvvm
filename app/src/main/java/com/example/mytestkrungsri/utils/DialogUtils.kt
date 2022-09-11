package com.example.mytestkrungsri.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.mytestkrungsri.R

class DialogUtils {


    companion object {
        var dialogMsg: AlertDialog? = null

        fun dismissDialog() {
            if (dialogMsg != null && dialogMsg!!.isShowing) {
                dialogMsg?.dismiss()
                dialogMsg = null
            }
        }

        private fun getCustomViewDialog(activity: Activity, layout: Int): View {
            var factory = LayoutInflater.from(activity)
            return factory.inflate(layout, null)
        }

    }
}