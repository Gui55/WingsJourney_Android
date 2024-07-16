package com.example.wingsjourney.games.gamesdetails.data.repository

import com.example.core.domain.model.Game
import com.example.wingsjourney.games.gamesdetails.data.datasource.GameDetailsDataSource
import javax.inject.Inject

class GameDetailsRepositoryImpl @Inject constructor(
    private val gameDetailsDataSource: GameDetailsDataSource
) : GameDetailsRepository {
    override suspend fun getGameDetails(token: String, id: Long): Game {
        return gameDetailsDataSource.getDetails(token, id)
    }
}