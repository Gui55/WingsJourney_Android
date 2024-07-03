package com.example.wingsjourney.games.gameslist.framework.di

import com.example.wingsjourney.games.gameslist.usecase.GetGamesUseCase
import com.example.wingsjourney.games.gameslist.usecase.GetGamesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GamesListUseCaseModule {

    @Binds
    fun bindGetGamesUseCase(useCaseImpl: GetGamesUseCaseImpl) : GetGamesUseCase

}