package com.example.wingsjourney.login.data.repository

import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
): LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return loginDataSource.login(loginRequest)
    }
}