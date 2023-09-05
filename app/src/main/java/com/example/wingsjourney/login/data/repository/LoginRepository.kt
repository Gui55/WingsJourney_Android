package com.example.wingsjourney.login.data.repository

interface LoginRepository {
    fun login(user: String, password: String) : Boolean
}