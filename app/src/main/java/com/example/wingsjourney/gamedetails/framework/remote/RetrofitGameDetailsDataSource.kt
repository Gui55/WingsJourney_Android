package com.example.wingsjourney.gamedetails.framework.remote

import com.example.wingsjourney.gamedetails.data.datasource.GameDetailsDataSource
import com.example.wingsjourney.gamedetails.domain.model.Game
import com.example.wingsjourney.network.GamesRetrofitApi
import javax.inject.Inject

class RetrofitGameDetailsDataSource @Inject constructor(
    private val gamesRetrofitApi: GamesRetrofitApi
) : GameDetailsDataSource {
    override suspend fun getDetails(token: String, id: Long): Game {
        return gamesRetrofitApi.getGameDetails(token, id)
    }
}