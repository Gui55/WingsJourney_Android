package com.example.wingsjourney.games.gamesdetails.network

import com.example.core.domain.model.Game
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GameDetailsRetrofitApi {

    @GET("games/id/{id}")
    suspend fun getGameDetails(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ) : Game

}