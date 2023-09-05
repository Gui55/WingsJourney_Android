package com.example.wingsjourney.login.framework.remote

import com.example.wingsjourney.login.data.repository.LoginDataSource
import com.example.wingsjourney.login.framework.network.LoginApi
import javax.inject.Inject

class MockLoginDataSource @Inject constructor(
    private val loginApi: LoginApi
) : LoginDataSource {
    override fun login(user: String, password: String): Boolean {
        return loginApi.login(user, password)
    }
}