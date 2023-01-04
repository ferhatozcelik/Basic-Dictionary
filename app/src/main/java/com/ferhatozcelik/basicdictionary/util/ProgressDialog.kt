package com.ferhatozcelik.basicdictionary.util

import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.ferhatozcelik.basicdictionary.R

class ProgressDialog(var activity: Activity) {
    val builder = AlertDialog.Builder(activity)
    var alertDialog = builder.create()
    var prosses: Boolean = false

    fun createProgressDialog() {
        prosses = false
        val view: View = LayoutInflater.from(activity).inflate(
            R.layout.progress_dialog,
            activity.findViewById<View>(R.id.layoutDialogContainer) as RelativeLayout?
        )
        builder.setView(view)
        alertDialog = builder.create()

        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }

    }

    fun showDialog() {
        if (!prosses) {
            alertDialog.setCancelable(false)
            alertDialog.show()
            prosses = true
        }
    }

    fun cancelNowDialog() {
        alertDialog.cancel()
        prosses = false
    }

    fun cancelDelayDialog() {
        Handler().postDelayed({alertDialog.cancel()
            prosses = false
        }, 600)

    }

}