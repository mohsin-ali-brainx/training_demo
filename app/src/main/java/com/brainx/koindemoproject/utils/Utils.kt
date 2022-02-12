package com.brainx.koindemoproject.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

@SuppressLint("CheckResult")
fun ImageView.loadImage(
    image: Any?,
    placeHolder: Int? = null,
    errorImage: Int? = null,
    loadingProgress: ProgressBar? = null,
    isCaching: Boolean = true,
    listener: RequestListener<Drawable>? = null
) {
    try {
        val nullAt = "null"
        loadingProgress?.visibility = View.VISIBLE
        val reqManager = Glide.with(context)

        val requestCreator = when (image) {
            is String -> {
                if (URLUtil.isValidUrl(image))
                    reqManager.load(if (image.isNotEmpty()) image else nullAt)
                else {
                    reqManager.load(File(if (image.isNotEmpty()) image else nullAt))
                }
            }
            is Int -> reqManager.load(image)
            is File -> reqManager.load(image)
            is Uri -> reqManager.load(image)
            is Drawable -> reqManager.load(image)
            is Bitmap -> reqManager.load(image)
            else -> reqManager.load(nullAt)
        }
        if (!isCaching) {
            requestCreator.diskCacheStrategy(DiskCacheStrategy.NONE)
            requestCreator.skipMemoryCache(true)
        }

        if (placeHolder != null)
            requestCreator.placeholder(placeHolder)
        if (errorImage != null)
            requestCreator.error(errorImage)

        requestCreator.listener(object : RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                loadingProgress?.visibility = View.GONE
                listener?.onLoadFailed(e, model, target, isFirstResource)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                loadingProgress?.visibility = View.GONE
                listener?.onResourceReady(resource, model, target, dataSource, isFirstResource)
                return false
            }
        })
        requestCreator.into(this)
    } catch (ex: Exception) {
    }

}

fun View.beInvisibleIf(beInvisible: Boolean) = if (beInvisible) beInvisible() else beVisible()

fun View.beVisibleIf(beVisible: Boolean) = if (beVisible) beVisible() else beGone()

fun View.beGoneIf(beGone: Boolean) = beVisibleIf(!beGone)

fun View.beInvisible() {
    visibility = View.INVISIBLE
}

fun View.beVisible() {
    visibility = View.VISIBLE
}

fun View.beGone() {
    visibility = View.GONE
}

fun View.beVisibleOrGone(stats: Boolean) {
    visibility = if (stats) View.VISIBLE else View.GONE
}

fun Activity.showToast(message: Any?) {
    val messageString = when (message) {
        is String -> message
        is Int -> getString(message)
        else -> null
    }
    if (messageString.isNullOrEmpty()) return
    try {
        Toast.makeText(this, messageString, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
    }
}