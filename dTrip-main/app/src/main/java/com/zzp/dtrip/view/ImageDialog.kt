package com.zzp.dtrip.view

import android.app.AlertDialog
import android.content.Context
import android.widget.ImageView
import com.zzp.dtrip.data.DataMessage

class ImageDialog(private val context: Context, private val imageResId: Int, private val  message: String) {

    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("请前往浏览器打开")
        builder.setMessage(message)
        val imageView = ImageView(context)
        imageView.setImageResource(imageResId)
        builder.setView(imageView)
        builder.setPositiveButton("确定") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}