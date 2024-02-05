package com.example.wingsjourney.gamedetails.framework.di

import com.example.wingsjourney.gamedetails.data.datasource.GameDetailsDataSource
import com.example.wingsjourney.gamedetails.data.repository.GameDetailsRepository
import com.example.wingsjourney.gamedetails.data.repository.GameDetailsRepositoryImpl
import com.example.wingsjourney.gamedetails.framework.remote.RetrofitGameDetailsDataSource
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