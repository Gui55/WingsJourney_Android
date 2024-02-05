package com.example.wingsjourney.network

import com.example.wingsjourney.gamedetails.domain.model.Game
import com.example.wingsjourney.gameslist.domain.model.GeneralGameInfo
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface GamesRetrofitApi {
    @POST("authenticate")
    suspend fun doAuthentication(
        @Body
        request: LoginRequest
    ) : LoginResponse

    @GET("games")
    suspend fun getGames(@Header("Authorization") token: String) : List<GeneralGameInfo>

    @GET("games/id/{id}")
    suspend fun getGameDetails(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ) : Game

}