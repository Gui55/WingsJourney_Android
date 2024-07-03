package com.example.wingsjourney.games.gameslist.data.repository

import com.example.wingsjourney.games.gameslist.domain.model.GeneralGameInfo

interface GamesRepository {
    suspend fun getGames(token: String) : List<GeneralGameInfo>
}