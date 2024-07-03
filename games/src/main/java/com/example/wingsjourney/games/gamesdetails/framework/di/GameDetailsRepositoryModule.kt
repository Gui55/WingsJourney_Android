package com.example.wingsjourney.games.gamesdetails.framework.di

import com.example.wingsjourney.games.gamesdetails.data.datasource.GameDetailsDataSource
import com.example.wingsjourney.games.gamesdetails.data.repository.GameDetailsRepository
import com.example.wingsjourney.games.gamesdetails.data.repository.GameDetailsRepositoryImpl
import com.example.wingsjourney.games.gamesdetails.framework.remote.RetrofitGameDetailsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GameDetailsRepositoryModule {

    //Parâmetro -> Implementação
    //Retorno -> A interface

    @Binds
    fun bindGameDetailsRepository(gameDetailsRepositoryImpl: GameDetailsRepositoryImpl) :
            GameDetailsRepository

    @Binds
    fun bindGameDetailsDataSource(retrofitGameDetailsDataSource: RetrofitGameDetailsDataSource) :
            GameDetailsDataSource

}