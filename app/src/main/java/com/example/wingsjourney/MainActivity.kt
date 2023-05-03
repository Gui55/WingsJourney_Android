package com.example.wingsjourney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView

    lateinit var gamesApi: GamesApi

    val gamesAdapter = GamesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_games_list)
        gamesApi = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GamesApi::class.java)

        setupRecyclerView()
        setupFetchCharacters()
    }

    private fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = gamesAdapter
    }

    private fun setupFetchCharacters(){
        gamesApi.getGames().enqueue(object : Callback<List<Game>>{
            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                gamesAdapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {

            }

        })
    }

}