package com.example.wingsjourney.login.framework.di

import com.example.wingsjourney.login.data.repository.LoginDataSource
import com.example.wingsjourney.login.data.repository.LoginRepository
import com.example.wingsjourney.login.data.repository.LoginRepositoryImpl
import com.example.wingsjourney.login.framework.remote.RetrofitLoginDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginRepositoryModule {

    @Binds
    fun bindLoginRepository(impl: LoginRepositoryImpl) : LoginRepository

    @Binds
    fun bindLoginDataSource(impl: RetrofitLoginDataSource) : LoginDataSource

}