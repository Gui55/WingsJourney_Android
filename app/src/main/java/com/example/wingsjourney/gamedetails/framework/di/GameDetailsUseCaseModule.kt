package com.example.wingsjourney.gamedetails.framework.di

import com.example.wingsjourney.gamedetails.usecase.GetGameDetailsUseCase
import com.example.wingsjourney.gamedetails.usecase.GetGameDetailsUseCaseImpl
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