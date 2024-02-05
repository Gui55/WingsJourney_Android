package com.example.wingsjourney.gameslist.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wingsjourney.databinding.LayoutGameItemBinding
import com.example.wingsjourney.gameslist.domain.model.GeneralGameInfo
import com.example.wingsjourney.framework.imageloader.ImageLoader
import com.example.wingsjourney.util.OnGameItemClick

class GamesViewHolder(
    binding: LayoutGameItemBinding,
    private val imageLoader: ImageLoader,
    private val onGameItemClick: OnGameItemClick
) : ViewHolder(binding.root) {
    private val gameName = binding.tvGameName
    private val gameImage = binding.imGameImage

    fun bind(generalGameInfo: GeneralGameInfo, token: String){
        gameName.text = generalGameInfo.name
        imageLoader.load(
            gameImage,
            generalGameInfo.image,
            token
        )
        itemView.setOnClickListener{
            onGameItemClick(generalGameInfo.id, generalGameInfo.name)
        }
    }

    companion object{
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader,
            onGameItemClick: OnGameItemClick
        ): GamesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = LayoutGameItemBinding.inflate(inflater, parent, false)
            return GamesViewHolder(itemBinding, imageLoader, onGameItemClick)
        }
    }
}