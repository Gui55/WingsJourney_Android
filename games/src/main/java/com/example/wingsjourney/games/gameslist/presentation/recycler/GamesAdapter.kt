package com.example.wingsjourney.games.gameslist.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wingsjourney.base.framework.imageloader.ImageLoader
import com.example.wingsjourney.base.util.OnGameItemClick
import com.example.core.domain.model.GeneralGameInfo

class GamesAdapter(
    private val imageLoader: ImageLoader,
    private val onGameItemClick: OnGameItemClick
) : ListAdapter<GeneralGameInfo, GamesViewHolder>(diffCallback) {

    private var token = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder.create(parent, imageLoader, onGameItemClick)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, token)
        }
    }

    fun setToken(token: String){
        this.token = token
    }

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<GeneralGameInfo>(){
            override fun areItemsTheSame(oldItem: GeneralGameInfo, newItem: GeneralGameInfo): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: GeneralGameInfo, newItem: GeneralGameInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}