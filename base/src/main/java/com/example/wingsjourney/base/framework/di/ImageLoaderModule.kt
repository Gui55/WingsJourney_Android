package com.example.wingsjourney.base.framework.di

import com.example.wingsjourney.base.framework.imageloader.GlideImageLoader
import com.example.wingsjourney.base.framework.imageloader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface ImageLoaderModule {
    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader) : ImageLoader
}