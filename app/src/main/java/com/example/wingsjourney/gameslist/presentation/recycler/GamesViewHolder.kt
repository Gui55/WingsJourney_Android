package com.example.wingsjourney.gameslist.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wingsjourney.R
import com.example.wingsjourney.databinding.LayoutGameItemBinding
import com.example.wingsjourney.gameslist.domain.model.Game

class GamesViewHolder(binding: LayoutGameItemBinding) : ViewHolder(binding.root) {
    private val gameName = binding.tvGameName
    private val gameImage = binding.imGameImage

    fun bind(game: Game){
        gameName.text = game.name
        Glide.with(itemView)
            .load(game.image)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(gameImage)
    }

    companion object{
        fun create(parent: ViewGroup): GamesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = LayoutGameItemBinding.inflate(inflater, parent, false)
            return GamesViewHolder(itemBinding)
        }
    }
}