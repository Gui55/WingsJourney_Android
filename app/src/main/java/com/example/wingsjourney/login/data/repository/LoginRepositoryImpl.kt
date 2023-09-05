package com.example.wingsjourney.login.data.repository

import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
): LoginRepository {
    override fun login(user: String, password: String): Boolean {
        return loginDataSource.login(user, password)
    }
}