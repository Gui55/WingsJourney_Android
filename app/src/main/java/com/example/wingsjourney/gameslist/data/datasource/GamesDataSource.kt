package com.example.wingsjourney.gameslist.data.datasource

import com.example.wingsjourney.gameslist.domain.model.GeneralGameInfo

interface GamesDataSource {
    suspend fun fetchGames(token: String) : List<GeneralGameInfo>
}