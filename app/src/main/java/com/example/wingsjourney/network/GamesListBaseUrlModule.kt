package com.example.wingsjourney.network

import com.example.wingsjourney.BuildConfig
import com.example.wingsjourney.gameslist.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GamesListBaseUrlModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

}