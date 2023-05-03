package com.example.wingsjourney

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class GamesAdapter : ListAdapter<Game, GamesViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
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