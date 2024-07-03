package com.example.wingsjourney.login.framework.remote

import com.example.wingsjourney.login.data.repository.LoginDataSource
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import com.example.wingsjourney.login.network.LoginRetrofitApi
import javax.inject.Inject

class RetrofitLoginDataSource @Inject constructor(
    private val loginApi: LoginRetrofitApi
) : LoginDataSource {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return loginApi.doAuthentication(loginRequest)
    }
}