package com.example.testingutils.model

import com.example.core.domain.model.GeneralGameInfo

class GamesGeneralInfoFactory {

    fun create(gameProduct: GameProduct) = when(gameProduct){
        is GameProduct.AssassinsCreed -> GeneralGameInfo(
            id = 1,
            name = "Assassins Creed",
            image = "https://upload.wikimedia.org/wikipedia/pt/d/d0/Assassins_Creed_1_capa.png"
        )
        is GameProduct.XenobladeChronicles -> GeneralGameInfo(
            id = 2,
            name = "Xenoblade Chronicles",
            image = "https://upload.wikimedia.org/wikipedia/pt/9/96/Xenoblade_Chronicles_capa.png"
        )
    }

    sealed class GameProduct{
        data object AssassinsCreed : GameProduct()
        data object XenobladeChronicles : GameProduct()
    }

}