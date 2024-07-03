package com.example.wingsjourney.games.gamesdetails.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GameDetailsNetworkModule {

    @Provides
    fun provideGamesApiInterface(
        retrofit: Retrofit
    ) : GameDetailsRetrofitApi {
        return retrofit.create(GameDetailsRetrofitApi::class.java)
    }

}