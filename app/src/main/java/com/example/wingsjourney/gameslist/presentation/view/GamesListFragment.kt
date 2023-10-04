package com.example.wingsjourney.gameslist.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingsjourney.R
import com.example.wingsjourney.databinding.FragmentGamesListBinding
import com.example.wingsjourney.framework.imageloader.ImageLoader
import com.example.wingsjourney.gameslist.presentation.recycler.GamesAdapter
import com.example.wingsjourney.gameslist.presentation.viewmodel.GamesViewModel
import com.example.wingsjourney.usecase.base.ResultStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GamesListFragment : Fragment() {

    private var _binding: FragmentGamesListBinding? = null
    private val binding: FragmentGamesListBinding get() = _binding!!

    private lateinit var gamesAdapter: GamesAdapter

    private val viewModel: GamesViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGamesListBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewConfig()
        setupViewModel()
    }

    private fun setupViewModel(){
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.jwt_shared_pref_key),
            Context.MODE_PRIVATE
        ) ?: return

        val token = sharedPref.getString(getString(R.string.jwt_shared_pref_key), "").toString()

        if (token.isNotEmpty()) {
            gamesAdapter.setToken(token)
            viewModel.lookForGames(token)
        }

        viewModel.gamesResult.observe(viewLifecycleOwner) { status ->
            when (status) {
                ResultStatus.Loading -> {}
                is ResultStatus.Success -> gamesAdapter.submitList(status.data)

                is ResultStatus.Error -> {
                    binding.rvGamesList.visibility = GONE
                    binding.tvError.text = status.throwable.message
                    binding.tvError.visibility = VISIBLE
                }
            }
        }
    }

    private fun recyclerViewConfig(){
        gamesAdapter = GamesAdapter(imageLoader)
        setupRecyclerView()
    }
    private fun setupRecyclerView(){
        binding.rvGamesList.layoutManager = LinearLayoutManager(activity)
        binding.rvGamesList.adapter = gamesAdapter
    }

}