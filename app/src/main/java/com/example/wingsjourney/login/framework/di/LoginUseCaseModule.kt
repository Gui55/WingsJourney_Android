package com.example.wingsjourney.login.framework.di

import com.example.wingsjourney.login.usecase.LoginUseCase
import com.example.wingsjourney.login.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginUseCaseModule {
    @Binds
    fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
}