package com.example.testingutils.model

import com.example.core.domain.model.Game

class GamesFactory {

    fun create(gameProduct: GameProduct) = when(gameProduct){
        is GameProduct.AssassinsCreed -> Game(
            id = 1,
            name = "Assassins Creed",
            company = "Ubisoft",
            image = "https://upload.wikimedia.org/wikipedia/pt/d/d0/Assassins_Creed_1_capa.png",
            genre = "Action",
            description = "Lorem ipsum",
            likes = 0,
            dislikes = 0
        )
        is GameProduct.XenobladeChronicles -> Game(
            id = 2,
            name = "Xenoblade Chronicles",
            company = "Monolith Soft",
            image = "https://upload.wikimedia.org/wikipedia/pt/9/96/Xenoblade_Chronicles_capa.png",
            genre = "RPG",
            description = "Lorem ipsum 2",
            likes = 0,
            dislikes = 0
        )
    }

    sealed class GameProduct{
        object AssassinsCreed : GameProduct()
        object XenobladeChronicles : GameProduct()
    }

}