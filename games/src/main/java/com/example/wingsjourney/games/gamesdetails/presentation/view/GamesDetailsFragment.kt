package com.example.wingsjourney.games.gamesdetails.presentation.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.core.usecase.base.ResultStatus
import com.example.wingsjourney.base.framework.imageloader.ImageLoader
import com.example.wingsjourney.base.util.TokenGetter
import com.example.wingsjourney.games.R
import com.example.wingsjourney.games.databinding.FragmentGamesDetailsBinding
import com.example.core.domain.model.Game
import com.example.wingsjourney.games.gamesdetails.presentation.viewmodel.GamesDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameDetailsFragment : Fragment() {

    private var _binding: FragmentGamesDetailsBinding? = null
    private val binding: FragmentGamesDetailsBinding get() = _binding!!

    private val viewModel: GamesDetailsViewModel by viewModels()

    private val args by navArgs<GameDetailsFragmentArgs>()

    private lateinit var token: String

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGamesDetailsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = TokenGetter.getToken(context)
        setupObservation()
        viewModel.getGameDetails(token, args.id)
        binding.tvGameDescription.movementMethod = ScrollingMovementMethod()
    }

    private fun setupObservation(){
        viewModel.gameDetailsResult.observe(viewLifecycleOwner){ status ->
            when(status){
                ResultStatus.Loading -> {}
                is ResultStatus.Success -> fulfillDetails(status.data)
                is ResultStatus.Error -> Toast.makeText(
                    context,
                    getString(R.string.error_loading_details),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun fulfillDetails(game: Game){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = game.name

        imageLoader.load(
            binding.ivGameImage,
            game.image,
            token
        )

        binding.tvGameGenreAndCompany.text = getString(
            R.string.genre_and_company_details,
            game.genre,
            game.company
        )

        binding.tvGameDescription.text = game.description
    }

}