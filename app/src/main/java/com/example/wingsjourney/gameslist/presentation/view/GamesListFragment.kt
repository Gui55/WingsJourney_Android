package com.example.wingsjourney.gameslist.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingsjourney.gameslist.presentation.recycler.GamesAdapter
import com.example.wingsjourney.databinding.FragmentGamesListBinding
import com.example.wingsjourney.gameslist.domain.model.Game
import com.example.wingsjourney.gameslist.presentation.viewmodel.GamesViewModel
import com.example.wingsjourney.usecase.base.ResultStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesListFragment : Fragment() {

    private var _binding: FragmentGamesListBinding? = null
    private val binding: FragmentGamesListBinding get() = _binding!!

    private lateinit var gamesAdapter: GamesAdapter

    private val viewModel: GamesViewModel by viewModels()

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
        viewModel.lookForGames()
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
        gamesAdapter = GamesAdapter()
        setupRecyclerView()
    }
    private fun setupRecyclerView(){
        binding.rvGamesList.layoutManager = LinearLayoutManager(activity)
        binding.rvGamesList.adapter = gamesAdapter
    }

}