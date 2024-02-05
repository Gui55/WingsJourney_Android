package com.example.wingsjourney.gamedetails.data.repository

import com.example.wingsjourney.gamedetails.data.datasource.GameDetailsDataSource
import com.example.wingsjourney.gamedetails.domain.model.Game
import javax.inject.Inject

class GameDetailsRepositoryImpl @Inject constructor(
    private val gameDetailsDataSource: GameDetailsDataSource
) : GameDetailsRepository {
    override suspend fun getGameDetails(token: String, id: Long): Game {
        return gameDetailsDataSource.getDetails(token, id)
    }
}