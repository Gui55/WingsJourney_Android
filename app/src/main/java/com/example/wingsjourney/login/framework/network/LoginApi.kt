package com.example.wingsjourney.login.framework.network

class LoginApi {
    fun login(user: String, password: String): Boolean {
        return user == "admin" && password == "admin"
    }
}