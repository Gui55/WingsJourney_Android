package com.example.wingsjourney.games.gamesdetails.data.repository

import com.example.wingsjourney.games.domain.model.Game

interface GameDetailsRepository {
    suspend fun getGameDetails(token: String, id: Long) : Game
}