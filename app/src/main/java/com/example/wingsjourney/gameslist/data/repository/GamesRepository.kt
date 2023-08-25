package com.example.wingsjourney.gameslist.data.repository

import com.example.wingsjourney.gameslist.domain.model.Game

interface GamesRepository {
    suspend fun getGames() : List<Game>
}