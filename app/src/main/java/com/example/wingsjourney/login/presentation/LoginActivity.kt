package com.example.wingsjourney.login.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.wingsjourney.R
import com.example.wingsjourney.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //A FragmentContainerView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.login_nav_host_container) as NavHostFragment

        //O NavController é pego do NavHostFragment
        navController = navHostFragment.navController

        //Neste construtor está sendo passada as Fragments "Top Level"
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment
            )
        )

        //Aplicando a navegação na Toolbar
        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)

        //Configuração para botão de voltar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination) {
                binding.toolbarApp.setNavigationIcon(R.drawable.ic_back)
            }
        }
    }
}