package com.example.wingsjourney.games.gamesdetails.data.datasource

import com.example.core.domain.model.Game

interface GameDetailsDataSource {
    suspend fun getDetails(token: String, id: Long) : Game
}