package com.example.wingsjourney.login.usecase

import com.example.wingsjourney.login.data.repository.LoginRepository
import com.example.wingsjourney.usecase.base.ResultStatus
import com.example.wingsjourney.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LoginUseCase {
    operator fun invoke(params: LoginParams): Flow<ResultStatus<Boolean>>
    data class LoginParams(val user: String, val password: String)
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase, UseCase<LoginUseCase.LoginParams, Boolean>(){
    override suspend fun doWork(params: LoginUseCase.LoginParams): ResultStatus<Boolean> {
        val loginSuccess = loginRepository.login(params.user, params.password)
        return ResultStatus.Success(loginSuccess)
    }
}