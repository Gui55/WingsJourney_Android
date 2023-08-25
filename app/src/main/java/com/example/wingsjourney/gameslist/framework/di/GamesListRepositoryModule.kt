package com.example.wingsjourney.gameslist.framework.di

import com.example.wingsjourney.gameslist.data.repository.GamesDataSource
import com.example.wingsjourney.gameslist.data.repository.GamesRepository
import com.example.wingsjourney.gameslist.data.repository.GamesRepositoryImpl
import com.example.wingsjourney.gameslist.framework.remote.RetrofitGamesListDataSource
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