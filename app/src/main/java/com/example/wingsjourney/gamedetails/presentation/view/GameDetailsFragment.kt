package com.example.wingsjourney.gamedetails.presentation.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.wingsjourney.R
import com.example.wingsjourney.base.framework.imageloader.ImageLoader
import com.example.wingsjourney.databinding.FragmentGameDetailsBinding
import com.example.wingsjourney.gamedetails.domain.model.Game
import com.example.wingsjourney.gamedetails.presentation.viewmodel.GameDetailsViewModel
import com.example.wingsjourney.usecase.base.ResultStatus
import com.example.wingsjourney.util.TokenGetter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameDetailsFragment : Fragment() {

    private var _binding: FragmentGameDetailsBinding? = null
    private val binding: FragmentGameDetailsBinding get() = _binding!!

    private val viewModel: GameDetailsViewModel by viewModels()

    //private val args by navArgs<GameDetailsFragmentArgs>()

    private lateinit var token: String

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGameDetailsBinding.inflate(
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
        //viewModel.getGameDetails(token, args.id)
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