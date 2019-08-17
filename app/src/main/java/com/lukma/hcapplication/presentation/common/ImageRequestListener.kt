package com.lukma.hcapplication.presentation.common

import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ImageRequestListener(private val progressBar: ProgressBar) : RequestListener<Drawable> {
    init {
        progressBar.isVisible = true
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        progressBar.isVisible = false
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        progressBar.isVisible = false
        return false
    }
}
