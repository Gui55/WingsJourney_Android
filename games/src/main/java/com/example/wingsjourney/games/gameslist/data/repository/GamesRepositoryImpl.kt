package com.example.wingsjourney.games.gameslist.data.repository

import com.example.wingsjourney.games.gameslist.data.datasource.GamesDataSource
import com.example.core.domain.model.GeneralGameInfo
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesDataSource: GamesDataSource
) : GamesRepository {
    override suspend fun getGames(token: String): List<GeneralGameInfo> {
        return gamesDataSource.fetchGames(token)
    }
}