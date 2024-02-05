package com.example.wingsjourney.gameslist.framework.remote

import com.example.wingsjourney.gameslist.domain.model.GeneralGameInfo
import com.example.wingsjourney.gameslist.data.datasource.GamesDataSource
import com.example.wingsjourney.network.GamesRetrofitApi
import javax.inject.Inject

class RetrofitGamesListDataSource @Inject constructor(
    private val gamesRetrofitApi: GamesRetrofitApi
) : GamesDataSource {
    override suspend fun fetchGames(token: String): List<GeneralGameInfo> {
        return gamesRetrofitApi.getGames(token)
    }
}