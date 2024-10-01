package com.zzp.dtrip.view

import android.app.AlertDialog
import android.content.Context


class CustomDialog(private val context: Context) {
    fun show(message: String) {
        val builder = AlertDialog.Builder(context)

        builder.setMessage(message)
            .setPositiveButton("确定") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}