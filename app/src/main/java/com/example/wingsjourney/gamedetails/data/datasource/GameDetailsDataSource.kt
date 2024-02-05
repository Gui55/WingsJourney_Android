package com.example.wingsjourney.gamedetails.data.datasource

import com.example.wingsjourney.gamedetails.domain.model.Game

interface GameDetailsDataSource {
    suspend fun getDetails(token: String, id: Long) : Game
}