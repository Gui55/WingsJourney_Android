package com.example.wingsjourney.network

import com.example.wingsjourney.gameslist.domain.model.Game
import com.example.wingsjourney.login.framework.network.request.LoginRequest
import com.example.wingsjourney.login.framework.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GamesRetrofitApi {

    @POST("authenticate")
    suspend fun doAuthentication(
        @Body
        request: LoginRequest
    ) : LoginResponse

    @GET("games")
    suspend fun getGames() : List<Game>

}