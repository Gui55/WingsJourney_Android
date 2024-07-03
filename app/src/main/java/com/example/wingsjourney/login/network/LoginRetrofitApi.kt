package com.example.wingsjourney.login.network

import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitApi {
    @POST("authenticate")
    suspend fun doAuthentication(
        @Body
        request: LoginRequest
    ) : LoginResponse

}