package com.example.wingsjourney.gameslist.data.repository

import com.example.wingsjourney.gameslist.domain.model.Game

interface GamesDataSource {
    suspend fun fetchGames() : List<Game>
}