package com.example.wingsjourney.base.framework.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.wingsjourney.base.util.LocalHostNameUtils
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader {
    override fun load(
        imageView: ImageView,
        imageUrl: String,
        token: String,
        placeholder: Int,
        fallback: Int
    ) {
        val glideUrl = GlideUrl(
            LocalHostNameUtils.replaceLocalHostString(imageUrl),
            LazyHeaders.Builder()
                .addHeader("Authorization", token)
                .build()
        )

        Glide.with(imageView.rootView)
            .load(glideUrl)
            .placeholder(placeholder)
            .fallback(fallback)
            .into(imageView)
    }
}