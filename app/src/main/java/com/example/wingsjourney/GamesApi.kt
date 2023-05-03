package com.example.wingsjourney

import retrofit2.Call
import retrofit2.http.GET

interface GamesApi {

    @GET("games")
    fun getGames() : Call<List<Game>>

}