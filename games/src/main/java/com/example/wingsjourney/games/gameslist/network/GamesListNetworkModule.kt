package com.example.wingsjourney.games.gameslist.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
object GamesListNetworkModule {

//    @Inject
//    lateinit var retrofit: Retrofit

    @Provides
    fun provideGamesApiInterface(
        retrofit: Retrofit
    ) : GamesRetrofitApi {
        return retrofit.create(GamesRetrofitApi::class.java)
    }
}