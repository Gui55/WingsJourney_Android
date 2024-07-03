package com.example.wingsjourney.login.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object LoginNetworkModule {

    @Provides
    fun provideGamesApiInterface(
        retrofit: Retrofit
    ) : LoginRetrofitApi {
        return retrofit.create(LoginRetrofitApi::class.java)
    }
}