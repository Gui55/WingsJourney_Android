package com.example.wingsjourney.gameslist.framework.di

import com.example.wingsjourney.gameslist.usecase.GetGamesUseCase
import com.example.wingsjourney.gameslist.usecase.GetGamesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GameListUseCaseModule {

    @Binds
    fun bindGetGamesUseCase(useCaseImpl: GetGamesUseCaseImpl) : GetGamesUseCase

}