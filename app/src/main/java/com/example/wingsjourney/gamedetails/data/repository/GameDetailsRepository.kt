package com.example.wingsjourney.gamedetails.data.repository

import com.example.wingsjourney.gamedetails.domain.model.Game

interface GameDetailsRepository {
    suspend fun getGameDetails(token: String, id: Long) : Game
}