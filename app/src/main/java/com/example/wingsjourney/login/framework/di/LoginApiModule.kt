package com.example.wingsjourney.login.framework.di

import com.example.wingsjourney.login.framework.network.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginApiModule {

    @Provides
    fun provideLoginApi() = LoginApi()

}