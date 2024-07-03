package com.example.wingsjourney.games.gameslist.framework.remote

import com.example.wingsjourney.games.gameslist.data.datasource.GamesDataSource
import com.example.wingsjourney.games.gameslist.domain.model.GeneralGameInfo
import com.example.wingsjourney.games.gameslist.network.GamesRetrofitApi
import javax.inject.Inject

class RetrofitGamesListDataSource @Inject constructor(
    private val gamesRetrofitApi: GamesRetrofitApi
) : GamesDataSource {
    override suspend fun fetchGames(token: String): List<GeneralGameInfo> {
        return gamesRetrofitApi.getGames(token)
    }
}