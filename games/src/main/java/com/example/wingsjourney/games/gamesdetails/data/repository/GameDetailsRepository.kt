package com.example.wingsjourney.games.gamesdetails.data.repository

import com.example.core.domain.model.Game

interface GameDetailsRepository {
    suspend fun getGameDetails(token: String, id: Long) : Game
}