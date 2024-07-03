package com.example.wingsjourney.games.domain.model

data class Game(
    val id: Long,
    val name: String,
    val company: String,
    val image: String,
    val genre: String,
    val description: String,
    val likes: Int,
    val dislikes: Int
)