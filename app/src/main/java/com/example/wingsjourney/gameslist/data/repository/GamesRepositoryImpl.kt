package com.example.wingsjourney.gameslist.data.repository

import com.example.wingsjourney.gameslist.domain.model.Game
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesDataSource: GamesDataSource
) : GamesRepository {
    override suspend fun getGames(): List<Game> {
        return gamesDataSource.fetchGames()
    }

}