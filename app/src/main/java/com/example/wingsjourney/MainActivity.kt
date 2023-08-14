package com.example.wingsjourney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.wingsjourney.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*lateinit var recyclerView : RecyclerView

    lateinit var gamesApi: GamesApi

    val gamesAdapter = GamesAdapter()*/

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavMain.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.gamesListFragment)
        )

        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination.id)
            if(!isTopLevelDestination){
                binding.toolbarApp.setNavigationIcon(R.drawable.ic_back)
            }
        }

        /*recyclerView = findViewById(R.id.rv_games_list)
        gamesApi = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GamesApi::class.java)

        setupRecyclerView()
        setupFetchCharacters()*/
    }

    /*private fun setupRecyclerView(){
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
    }*/

}