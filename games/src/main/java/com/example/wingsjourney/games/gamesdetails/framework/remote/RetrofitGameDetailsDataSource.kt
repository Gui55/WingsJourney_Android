package com.example.wingsjourney.games.gamesdetails.framework.remote

import com.example.wingsjourney.games.domain.model.Game
import com.example.wingsjourney.games.gamesdetails.data.datasource.GameDetailsDataSource
import com.example.wingsjourney.games.gamesdetails.network.GameDetailsRetrofitApi
import javax.inject.Inject

class RetrofitGameDetailsDataSource @Inject constructor(
    private val gameDetailsRetrofitApi: GameDetailsRetrofitApi
) : GameDetailsDataSource {
    override suspend fun getDetails(token: String, id: Long): Game {
        return gameDetailsRetrofitApi.getGameDetails(token, id)
    }
}