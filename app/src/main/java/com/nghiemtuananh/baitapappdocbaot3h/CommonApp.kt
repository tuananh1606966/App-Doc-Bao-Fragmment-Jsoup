package com.nghiemtuananh.baitapappdocbaot3h

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CommonApp {
    @JvmStatic
    @BindingAdapter("addImage")
    fun addImage(view: ImageView, resource: Int) {
        view.setImageResource(resource)
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String) {
//        if (url.startsWith("data")) {
//            var decodedString: ByteArray =
//                Base64.decode(url.substring("data:image/gif;base64,".length), Base64.DEFAULT)
//            var bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//            view.setImageBitmap(bitmap)
//        } else {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
//        }
    }
}