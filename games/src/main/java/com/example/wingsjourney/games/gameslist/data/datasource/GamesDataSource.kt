package com.example.wingsjourney.games.gameslist.data.datasource

import com.example.wingsjourney.games.gameslist.domain.model.GeneralGameInfo

interface GamesDataSource {
    suspend fun fetchGames(token: String) : List<GeneralGameInfo>
}