package com.example.wingsjourney.gameslist.data.repository

import com.example.wingsjourney.gameslist.data.datasource.GamesDataSource
import com.example.wingsjourney.gameslist.domain.model.GeneralGameInfo
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesDataSource: GamesDataSource
) : GamesRepository {
    override suspend fun getGames(token: String): List<GeneralGameInfo> {
        return gamesDataSource.fetchGames(token)
    }

}