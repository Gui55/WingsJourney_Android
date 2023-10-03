package com.example.wingsjourney.gameslist.framework.remote

import com.example.wingsjourney.gameslist.domain.model.Game
import com.example.wingsjourney.gameslist.data.repository.GamesDataSource
import com.example.wingsjourney.network.GamesRetrofitApi
import javax.inject.Inject

class RetrofitGamesListDataSource @Inject constructor(
    private val gamesRetrofitApi: GamesRetrofitApi
) : GamesDataSource {
    override suspend fun fetchGames(): List<Game> {
        return gamesRetrofitApi.getGames()
    }
}