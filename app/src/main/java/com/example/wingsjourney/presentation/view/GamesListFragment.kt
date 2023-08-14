package com.example.wingsjourney.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingsjourney.GamesAdapter
import com.example.wingsjourney.databinding.FragmentGamesListBinding
import com.example.wingsjourney.presentation.viewmodel.GamesViewModel

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
        gamesAdapter = GamesAdapter()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        binding.rvGamesList.layoutManager = LinearLayoutManager(activity)
        binding.rvGamesList.adapter = gamesAdapter
    }

}