package com.example.wingsjourney.games.gameslist.data.repository

import com.example.core.domain.model.GeneralGameInfo

interface GamesRepository {
    suspend fun getGames(token: String) : List<GeneralGameInfo>
}