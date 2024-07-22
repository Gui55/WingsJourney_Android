package com.example.wingsjourney.games.gameslist.data.datasource

import com.example.core.domain.model.GeneralGameInfo

interface GamesDataSource {
    suspend fun fetchGames(token: String) : List<GeneralGameInfo>
}