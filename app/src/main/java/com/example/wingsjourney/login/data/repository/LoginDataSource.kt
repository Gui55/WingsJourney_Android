package com.example.wingsjourney.login.data.repository

interface LoginDataSource {
    fun login(user: String, password: String) : Boolean
}