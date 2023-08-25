package com.example.wingsjourney.gameslist.framework.network

import com.example.wingsjourney.gameslist.domain.model.Game
import retrofit2.http.GET

interface GamesApi {

    @GET("games")
    suspend fun getGames() : List<Game>

}