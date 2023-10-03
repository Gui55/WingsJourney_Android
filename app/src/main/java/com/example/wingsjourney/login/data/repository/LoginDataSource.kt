package com.example.wingsjourney.login.data.repository

import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse

interface LoginDataSource {
    suspend fun login(loginRequest: LoginRequest) : LoginResponse
}