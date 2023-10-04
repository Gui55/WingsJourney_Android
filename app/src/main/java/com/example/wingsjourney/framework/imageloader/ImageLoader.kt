package com.example.wingsjourney.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.wingsjourney.R

interface ImageLoader {
    fun load(
        imageView: ImageView,
        imageUrl: String,
        token: String,
        @DrawableRes placeholder: Int = R.drawable.ic_launcher_foreground,
        @DrawableRes fallback: Int = R.drawable.ic_android_icon
    )
}