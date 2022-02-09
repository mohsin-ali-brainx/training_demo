package com.brainx.koindemoproject.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.brainx.koindemoproject.utils.loadImage

@BindingAdapter("imageUrl")
fun loadImageFromInternet(
    view: ImageView,
    imageData: Any?,
) {
    view.apply {
        try {
           loadImage(imageData)
        }catch (ex:Exception){
        }
    }
}