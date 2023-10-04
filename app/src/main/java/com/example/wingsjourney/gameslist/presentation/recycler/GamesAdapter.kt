package com.example.wingsjourney.gameslist.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wingsjourney.framework.imageloader.ImageLoader
import com.example.wingsjourney.gameslist.domain.model.Game

class GamesAdapter(private val imageLoader: ImageLoader) : ListAdapter<Game, GamesViewHolder>(diffCallback) {

    private var token = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder.create(parent, imageLoader)
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
        private val diffCallback = object : DiffUtil.ItemCallback<Game>(){
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }
}