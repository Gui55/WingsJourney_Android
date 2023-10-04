package com.example.wingsjourney.gameslist.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wingsjourney.databinding.LayoutGameItemBinding
import com.example.wingsjourney.gameslist.domain.model.Game
import com.example.wingsjourney.framework.imageloader.ImageLoader

class GamesViewHolder(
    binding: LayoutGameItemBinding,
    private val imageLoader: ImageLoader
) : ViewHolder(binding.root) {
    private val gameName = binding.tvGameName
    private val gameImage = binding.imGameImage

    fun bind(game: Game, token: String){
        gameName.text = game.name
        imageLoader.load(
            gameImage,
            game.image,
            token
        )
    }

    companion object{
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader
        ): GamesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = LayoutGameItemBinding.inflate(inflater, parent, false)
            return GamesViewHolder(itemBinding, imageLoader)
        }
    }
}