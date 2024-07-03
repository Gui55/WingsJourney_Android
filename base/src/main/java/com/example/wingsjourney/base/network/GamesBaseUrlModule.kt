package com.example.wingsjourney.base.network

import com.example.wingsjourney.base.BuildConfig
import com.example.wingsjourney.base.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GamesBaseUrlModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

}