package com.example.wingsjourney.games.gameslist.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GamesListNetworkModule {

    @Provides
    fun provideGamesApiInterface(
        retrofit: Retrofit
    ) : GamesRetrofitApi {
        return retrofit.create(GamesRetrofitApi::class.java)
    }
}