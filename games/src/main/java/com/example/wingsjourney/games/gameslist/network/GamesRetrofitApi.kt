package com.example.wingsjourney.games.gameslist.network

import com.example.core.domain.model.GeneralGameInfo
import retrofit2.http.GET
import retrofit2.http.Header

interface GamesRetrofitApi {

    @GET("games")
    suspend fun getGames(@Header("Authorization") token: String) : List<GeneralGameInfo>

}