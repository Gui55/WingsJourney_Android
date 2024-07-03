package com.example.wingsjourney.games.gamesdetails.framework.di

import com.example.wingsjourney.games.gamesdetails.usecase.GetGameDetailsUseCase
import com.example.wingsjourney.games.gamesdetails.usecase.GetGameDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GameDetailsUseCaseModule {

    @Binds
    fun bindGameDetailsUseCase(useCase: GetGameDetailsUseCaseImpl) :
            GetGameDetailsUseCase

}