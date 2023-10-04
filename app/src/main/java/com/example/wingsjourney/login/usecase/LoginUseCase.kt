package com.example.wingsjourney.login.usecase

import com.example.wingsjourney.login.data.repository.LoginRepository
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import com.example.wingsjourney.usecase.base.ResultStatus
import com.example.wingsjourney.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LoginUseCase {
    operator fun invoke(params: LoginParams): Flow<ResultStatus<LoginResponse>>
    data class LoginParams(val loginRequest: LoginRequest)
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase, UseCase<LoginUseCase.LoginParams, LoginResponse>(){
    override suspend fun doWork(params: LoginUseCase.LoginParams): ResultStatus<LoginResponse> {
        val loginSuccess = loginRepository.login(params.loginRequest)
        return ResultStatus.Success(loginSuccess)
    }
}