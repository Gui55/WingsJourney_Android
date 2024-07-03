package com.example.wingsjourney.games.gameslist.framework.di

import com.example.wingsjourney.games.gameslist.data.datasource.GamesDataSource
import com.example.wingsjourney.games.gameslist.data.repository.GamesRepository
import com.example.wingsjourney.games.gameslist.data.repository.GamesRepositoryImpl
import com.example.wingsjourney.games.gameslist.framework.remote.RetrofitGamesListDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GamesListRepositoryModule {

    @Binds
    fun bindGetGamesRepository(repositoryImpl: GamesRepositoryImpl) : GamesRepository

    @Binds
    fun bindGamesDataSource(retrofitGamesListDataSource: RetrofitGamesListDataSource) : GamesDataSource

}